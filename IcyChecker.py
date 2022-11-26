#!/usr/bin/python

import os
import re
import readline
import sys
import subprocess
import platform
import ParseLTL

argTrans = {"-modArith": "/useModularArithmetic", 
            "-instrumentGas": "/instrumentGas",
            "-noReentrancy": "/stubModel:skip",
            "-singleCallback": "/stubModel:callback",
            "-powerfulAdversary": "/stubModel:multipleCallbacks",
            "-txnsOnFields": "/txnsOnFields",
            "-checkPrePost:": "/prePostHarness /SliceFunctions:"}

solFile = ""
contractName = ""
specification = ""
verisolFlags = "/modelReverts /omitSourceLineInfo /LazyAllocNoMod /omitAxioms /doModSet /noPrf /noChk /omitDataValuesInTrace /QuantFreeAllocs /instrumentSums /omitBoogieHarness /createMainHarness /noCustomTypes /alias  /noNonlinearArith"
# verisolFlags = "/modelReverts /omitSourceLineInfo /LazyAllocNoMod /omitAxioms /doModSet /noPrf /noChk /omitDataValuesInTrace /QuantFreeAllocs /omitBoogieHarness /createMainHarness /noCustomTypes /alias  /noNonlinearArith"

# verisolFlags = "/modelReverts /omitSourceLineInfo /LazyAllocNoMod /omitAxioms /doModSet /noPrf /noChk /omitDataValuesInTrace /QuantFreeAllocs /instrumentSums /omitBoogieHarness /createMainHarness /noCustomTypes /alias  /noNonlinearArith /useNumericOperators /omitUnsignedSemantics /generateGetters /modelAssemblyAsHavoc /useMultiDim"
smartpulseDir = os.path.dirname(os.path.realpath(__file__))


def printUsage():
    print("Usage: ./SmartPulse.py [args] contract.sol contractName spec.spec")
    print("args:")
    print(" Behavioral Models:")
    print("  -modArith                   | Model integers using modular arithmetic rather than as mathematical integers")
    print("  -instrumentGas              | Instrument gas usage using model (assumes model comes from solc)")
    print(" Adversary Models:")
    print("  -noReentrancy               | Assume attacker cannot make reentrant calls ")
    print("  -singleCallback             | Assume attacker will only make one reentrant call")
    print("  -powerfulAdversary          | Assume attacker can make arbitrary reentrant calls")
    print(" Harness Modifiers:")
    print("  -tnxsOnFields               | Allow transactions to be issued to contracts in fields of main contract")
    print("  -checkPrePost:<fn1,fn2,...> | Check pre/post conditions of the specified functions")


def processArgs(args):
    global solFile
    global contractName
    global specification
    global verisolFlags

    print(len(args))
    if len(args) < 4:
        return 
    for i in range(1,len(args)):
        if i == len(args) - 3:
            solFile = args[i]

            if("CYGWIN" in platform.system()):
                stream = os.popen('cygpath -m ' + solFile);
                solFile = stream.read().strip()
            elif("Linux" in platform.system() and "Microsoft" in platform.release()):
                stream = os.popen('wslpath -m ' + solFile);
                solFile = stream.read().strip()
        elif i == len(args) - 2:
            contractName = args[i]
        elif i == len(args) - 1:
            specification = args[i]
        else:
            for key, value in argTrans.iteritems():
                if key in args[i]:
                    verisolFlags += " " + args[i].replace(key, value)


def solToBoogie():
    veriSolName = 'dotnet verisol/bin/Debug/VeriSol.dll'
    # veriSolName = 'VeriSol'
    # if("Linux" in platform.system() and "Microsoft" in platform.release()):
    #     veriSolName = 'dotnet verisol/bin/Debug/VeriSol.dll'
    verisolCall = [veriSolName, solFile, contractName]
    verisolCall.append(verisolFlags)

    print(verisolCall)
    verisolLog = open("verisolOutput.log", "w")
    verisolLog.write(" ".join(verisolCall))
    verisolLog.write(os.linesep)
    verisolLog.flush()

    print("Translating contract to Boogie...")

    try:
        process = subprocess.Popen(" ".join(verisolCall), stdin=None, stdout=verisolLog, stderr=subprocess.STDOUT, shell=True)
    except:
        print("Cannot open subprocess")
        sys.exit(1)

    process.wait()
    verisolLog.close()

    if process.returncode != 0:
        print("Could not translate with VeriSol, see verisolOutput.log for details")
        sys.exit(1)

def check():
    boogieFile = open("__SolToBoogieTest_out.bpl", "a")
    specFile = open(specification, "r")
    change_flag = False
    origin_spec = []
    new_spec = []

    for line in specFile.readlines():
        origin_spec.append(line)
        try:
            new_line = ParseLTL.run(contractName, line)
            if line != new_line:
                change_flag = True
                new_spec.append(new_line)
        except:
            new_spec.append(line)
            pass

    if change_flag == True:
        t = raw_input('Find spec to change, please check whether to replace[y/n]: ')
        if t == 'y':
            for line in new_spec:
                boogieFile.write(line + os.linesep)
                print(line)
        else:
            for line in origin_spec:
                boogieFile.write(line + os.linesep)
                print(line)            
    else:
        for line in origin_spec:
                boogieFile.write(line + os.linesep)
                print(line)
                
    boogieFile.close()
    specFile.close()

    try:
        process = subprocess.Popen("{0}/smartpulse/SmartPulse.sh __SolToBoogieTest_out.bpl".format(smartpulseDir), shell=True)
    except:
        print("Cannot open subprocess")
        sys.exit(1)

    process.wait()
    
    

processArgs(sys.argv)

if solFile == "":
    printUsage()
    sys.exit(1)


solToBoogie()
check()