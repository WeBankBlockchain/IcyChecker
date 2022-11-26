import re;
# import pandas as pd

varMapping = {}

def run(contract, spec):
    with open("replace.txt", 'r') as f:
        for line in f.readlines():
            line = line.replace('\n','')
            content = line.split(',')
            funname = '*' if content[1] == contract else content[1]
            varMapping[content[0] + funname + content[2]] = content[3]

    newline = ReplaceVar(contract, spec)
    return newline


def ReplaceVar(contract, spec):
    fun, vars = parseSpec(spec)
    for var in vars:
        curvar = var[0]
        if curvar.find("this.") == 0:
            fun = '*'
            curvar = curvar[5:]
            if curvar.find('.') == -1:
                return spec
        if varMapping.__contains__(contract+fun+curvar):
            replacevar = varMapping[contract+fun+curvar]
            if var[0].find("msg.") >= 0:
                return spec
            spec = spec.replace(var[0], replacevar)
            print("Find a replacable var: {} --> {}".format(var[0], replacevar))
    # print(spec)
    return spec


def parseSpec(spec):
    pos = spec.rfind(',')
    # print(pos)
    spec1, spec2 = spec[:pos+1], spec[pos+1:]
    cur = re.search('\w+\(\w*\.*(\*|\w+)(\(|[,])', spec1).group(1)
    vars = re.findall('((\[|\]|\w+)(\.(\[|\]|\w+))+)', spec2)
    # print(cur, vars)
    return cur, vars

# run("Counters", "// #LTLProperty: ![](finished(COSSD.zxcxc(sdsdd,sdsdd), counter._value < 0))")
