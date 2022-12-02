// Boogie program verifier version 2.4.1.10503, Copyright (c) 2003-2014, Microsoft.
// Command Line Options: /print:LockedFundsFixed.bpl /pretty:1 /noVerify __SolToBoogieTest_out.bpl

// #LTLProperty: [](!finished(*, Wallet.balanceOf(null) != 0 || Wallet.balanceOf(this) != 0))

type Ref = int;

type ContractName = int;

var null: Ref;

var Wallet: ContractName;

function {:smtdefined "x"} ConstantToRef(x: int) : Ref;

function BoogieRefToInt(x: Ref) : int;

function {:bvbuiltin "mod"} modBpl(x: int, y: int) : int;

function keccak256(x: int) : int;

function abiEncodePacked1(x: int) : int;

function _SumMapping_VeriSol(x: [Ref]int) : int;

function abiEncodePacked2(x: int, y: int) : int;

function abiEncodePacked1R(x: Ref) : int;

function abiEncodePacked2R(x: Ref, y: int) : int;

function {:smtdefined "((as const (Array Int Int)) 0)"} zeroRefIntArr() : [Ref]int;

function {:smtdefined "((as const (Array Int Int)) 0)"} zeroIntIntArr() : [int]int;

function {:smtdefined "((as const (Array Int Bool)) false)"} zeroRefBoolArr() : [Ref]bool;

function {:smtdefined "((as const (Array Int Bool)) false)"} zeroIntBoolArr() : [int]bool;

function {:smtdefined "((as const (Array Int Int)) 0)"} zeroRefRefArr() : [Ref]Ref;

function {:smtdefined "((as const (Array Int Int)) 0)"} zeroIntRefArr() : [int]Ref;

function nonlinearMul(x: int, y: int) : int;

function nonlinearDiv(x: int, y: int) : int;

function nonlinearPow(x: int, y: int) : int;

function nonlinearMod(x: int, y: int) : int;

var Balance: [Ref]int;

var DType: [Ref]ContractName;

var Alloc: [Ref]bool;

var balance_ADDR: [Ref]int;

var M_Ref_int_balance0: [Ref][Ref]int;

var sum_balance0: [Ref]int;

var Length: [Ref]int;

var revert: bool;

var gas: int;

var now: int;

procedure {:inline 1} FreshRefGenerator__success() returns (newRef: Ref);
  modifies Alloc;



procedure {:inline 1} Wallet_Wallet_NoBaseCtor__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
  modifies Balance, balance_Wallet, M_Ref_int_balance0, sum_balance0, Alloc;



procedure {:inline 1} Wallet_Wallet(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
  modifies revert, __tmp__Balance, __tmp__DType, __tmp__Alloc, __tmp__balance_ADDR, __tmp__M_Ref_int_balance0, __tmp__sum_balance0, __tmp__Length, __tmp__now, __tmp__balance_Wallet, Balance, balance_Wallet, M_Ref_int_balance0, sum_balance0, Alloc;



implementation Wallet_Wallet(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
  var __exception: bool;

    havoc __exception;
    revert := false;
    if (__exception)
    {
        __tmp__Balance := Balance;
        __tmp__DType := DType;
        __tmp__Alloc := Alloc;
        __tmp__balance_ADDR := balance_ADDR;
        __tmp__M_Ref_int_balance0 := M_Ref_int_balance0;
        __tmp__sum_balance0 := sum_balance0;
        __tmp__Length := Length;
        __tmp__now := now;
        __tmp__balance_Wallet := balance_Wallet;
        call Wallet_Wallet__fail(this, msgsender_MSG, msgvalue_MSG);
        assume revert || gas < 0;
    }
    else
    {
        call Wallet_Wallet__success(this, msgsender_MSG, msgvalue_MSG);
        assume !revert && gas >= 0;
    }
}



var {:access "this.balance[i0]=M_Ref_int_balance0[balance_Wallet[this]][i0]"} {:sum "sum(this.balance)=sum_balance0[balance_Wallet[this]]"} balance_Wallet: [Ref]Ref;

procedure {:public} {:inline 1} deposit_Wallet(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
  modifies revert, __tmp__Balance, __tmp__DType, __tmp__Alloc, __tmp__balance_ADDR, __tmp__M_Ref_int_balance0, __tmp__sum_balance0, __tmp__Length, __tmp__now, __tmp__balance_Wallet, gas, Balance, sum_balance0, M_Ref_int_balance0;



implementation deposit_Wallet(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
  var __exception: bool;

    havoc __exception;
    revert := false;
    if (__exception)
    {
        __tmp__Balance := Balance;
        __tmp__DType := DType;
        __tmp__Alloc := Alloc;
        __tmp__balance_ADDR := balance_ADDR;
        __tmp__M_Ref_int_balance0 := M_Ref_int_balance0;
        __tmp__sum_balance0 := sum_balance0;
        __tmp__Length := Length;
        __tmp__now := now;
        __tmp__balance_Wallet := balance_Wallet;
        call deposit_Wallet__fail(this, msgsender_MSG, msgvalue_MSG);
        assume revert || gas < 0;
    }
    else
    {
        call deposit_Wallet__success(this, msgsender_MSG, msgvalue_MSG);
        assume !revert && gas >= 0;
    }
}



procedure {:public} {:inline 1} withdraw_Wallet(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, amt_s46: int);
  modifies revert, __tmp__Balance, __tmp__DType, __tmp__Alloc, __tmp__balance_ADDR, __tmp__M_Ref_int_balance0, __tmp__sum_balance0, __tmp__Length, __tmp__now, __tmp__balance_Wallet, gas, sum_balance0, M_Ref_int_balance0, Balance;



implementation withdraw_Wallet(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, amt_s46: int)
{
  var __exception: bool;

    havoc __exception;
    revert := false;
    if (__exception)
    {
        __tmp__Balance := Balance;
        __tmp__DType := DType;
        __tmp__Alloc := Alloc;
        __tmp__balance_ADDR := balance_ADDR;
        __tmp__M_Ref_int_balance0 := M_Ref_int_balance0;
        __tmp__sum_balance0 := sum_balance0;
        __tmp__Length := Length;
        __tmp__now := now;
        __tmp__balance_Wallet := balance_Wallet;
        call withdraw_Wallet__fail(this, msgsender_MSG, msgvalue_MSG, amt_s46);
        assume revert || gas < 0;
    }
    else
    {
        call withdraw_Wallet__success(this, msgsender_MSG, msgvalue_MSG, amt_s46);
        assume !revert && gas >= 0;
    }
}



procedure {:public} {:inline 1} transfer_Wallet(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, to_s89: Ref, amt_s89: int);
  modifies revert, __tmp__Balance, __tmp__DType, __tmp__Alloc, __tmp__balance_ADDR, __tmp__M_Ref_int_balance0, __tmp__sum_balance0, __tmp__Length, __tmp__now, __tmp__balance_Wallet, gas, sum_balance0, M_Ref_int_balance0;



implementation transfer_Wallet(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, to_s89: Ref, amt_s89: int)
{
  var __exception: bool;

    havoc __exception;
    revert := false;
    if (__exception)
    {
        __tmp__Balance := Balance;
        __tmp__DType := DType;
        __tmp__Alloc := Alloc;
        __tmp__balance_ADDR := balance_ADDR;
        __tmp__M_Ref_int_balance0 := M_Ref_int_balance0;
        __tmp__sum_balance0 := sum_balance0;
        __tmp__Length := Length;
        __tmp__now := now;
        __tmp__balance_Wallet := balance_Wallet;
        call transfer_Wallet__fail(this, msgsender_MSG, msgvalue_MSG, to_s89, amt_s89);
        assume revert || gas < 0;
    }
    else
    {
        call transfer_Wallet__success(this, msgsender_MSG, msgvalue_MSG, to_s89, amt_s89);
        assume !revert && gas >= 0;
    }
}



procedure {:public} {:inline 1} balanceOf_Wallet(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, user_s101: Ref)
   returns (__ret_0_: int);
  modifies revert, __tmp__Balance, __tmp__DType, __tmp__Alloc, __tmp__balance_ADDR, __tmp__M_Ref_int_balance0, __tmp__sum_balance0, __tmp__Length, __tmp__now, __tmp__balance_Wallet, gas;



implementation balanceOf_Wallet(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, user_s101: Ref)
   returns (__ret_0_: int)
{
  var __exception: bool;

    havoc __exception;
    revert := false;
    if (__exception)
    {
        __tmp__Balance := Balance;
        __tmp__DType := DType;
        __tmp__Alloc := Alloc;
        __tmp__balance_ADDR := balance_ADDR;
        __tmp__M_Ref_int_balance0 := M_Ref_int_balance0;
        __tmp__sum_balance0 := sum_balance0;
        __tmp__Length := Length;
        __tmp__now := now;
        __tmp__balance_Wallet := balance_Wallet;
        call __ret_0_ := balanceOf_Wallet__fail(this, msgsender_MSG, msgvalue_MSG, user_s101);
        assume revert || gas < 0;
    }
    else
    {
        call __ret_0_ := balanceOf_Wallet__success(this, msgsender_MSG, msgvalue_MSG, user_s101);
        assume !revert && gas >= 0;
    }
}



procedure {:inline 1} FallbackDispatch__success(from: Ref, to: Ref, amount: int);
  modifies Balance, revert, gas, sum_balance0, M_Ref_int_balance0, __tmp__Balance, __tmp__DType, __tmp__Alloc, __tmp__balance_ADDR, __tmp__M_Ref_int_balance0, __tmp__sum_balance0, __tmp__Length, __tmp__now, __tmp__balance_Wallet;



procedure {:inline 1} Fallback_UnknownType__success(from: Ref, to: Ref, amount: int);
  modifies Balance, revert, gas, sum_balance0, M_Ref_int_balance0, __tmp__Balance, __tmp__DType, __tmp__Alloc, __tmp__balance_ADDR, __tmp__M_Ref_int_balance0, __tmp__sum_balance0, __tmp__Length, __tmp__now, __tmp__balance_Wallet;



procedure {:inline 1} send__success(from: Ref, to: Ref, amount: int) returns (success: bool);
  modifies __tmp__Balance, __tmp__DType, __tmp__Alloc, __tmp__balance_ADDR, __tmp__M_Ref_int_balance0, __tmp__sum_balance0, __tmp__Length, __tmp__now, __tmp__balance_Wallet, revert, gas, Balance, sum_balance0, M_Ref_int_balance0;



procedure CorralChoice_Wallet(this: Ref);
  modifies gas, now, Alloc, revert, __tmp__Balance, __tmp__DType, __tmp__Alloc, __tmp__balance_ADDR, __tmp__M_Ref_int_balance0, __tmp__sum_balance0, __tmp__Length, __tmp__now, __tmp__balance_Wallet, Balance, sum_balance0, M_Ref_int_balance0;



procedure main();
  modifies gas, Alloc, Balance, balance_Wallet, M_Ref_int_balance0, sum_balance0, now, revert, __tmp__Balance, __tmp__DType, __tmp__Alloc, __tmp__balance_ADDR, __tmp__M_Ref_int_balance0, __tmp__sum_balance0, __tmp__Length, __tmp__now, __tmp__balance_Wallet;



var __tmp__Balance: [Ref]int;

var __tmp__DType: [Ref]ContractName;

var __tmp__Alloc: [Ref]bool;

var __tmp__balance_ADDR: [Ref]int;

var __tmp__M_Ref_int_balance0: [Ref][Ref]int;

var __tmp__sum_balance0: [Ref]int;

var __tmp__Length: [Ref]int;

var __tmp__now: int;

var __tmp__balance_Wallet: [Ref]Ref;

procedure {:inline 1} FreshRefGenerator__fail() returns (newRef: Ref);
  modifies __tmp__Alloc;



procedure {:inline 1} Wallet_Wallet_NoBaseCtor__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
  modifies __tmp__Balance, __tmp__balance_Wallet, __tmp__M_Ref_int_balance0, __tmp__sum_balance0, __tmp__Alloc;



procedure {:inline 1} Wallet_Wallet__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
  modifies Balance, balance_Wallet, M_Ref_int_balance0, sum_balance0, Alloc;



procedure {:inline 1} Wallet_Wallet__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
  modifies __tmp__Balance, __tmp__balance_Wallet, __tmp__M_Ref_int_balance0, __tmp__sum_balance0, __tmp__Alloc;



procedure {:inline 1} deposit_Wallet__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
  modifies Balance, gas, sum_balance0, M_Ref_int_balance0;



procedure {:inline 1} deposit_Wallet__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
  modifies __tmp__Balance, gas, __tmp__sum_balance0, __tmp__M_Ref_int_balance0;



procedure {:inline 1} withdraw_Wallet__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, amt_s46: int);
  modifies gas, sum_balance0, M_Ref_int_balance0, revert, __tmp__Balance, __tmp__DType, __tmp__Alloc, __tmp__balance_ADDR, __tmp__M_Ref_int_balance0, __tmp__sum_balance0, __tmp__Length, __tmp__now, __tmp__balance_Wallet, Balance;



procedure {:inline 1} withdraw_Wallet__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, amt_s46: int);
  modifies gas, __tmp__sum_balance0, __tmp__M_Ref_int_balance0, revert, __tmp__Balance, __tmp__DType, __tmp__Alloc, __tmp__balance_ADDR, __tmp__Length, __tmp__now, __tmp__balance_Wallet;



procedure {:inline 1} transfer_Wallet__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, to_s89: Ref, amt_s89: int);
  modifies gas, revert, sum_balance0, M_Ref_int_balance0;



procedure {:inline 1} transfer_Wallet__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, to_s89: Ref, amt_s89: int);
  modifies gas, revert, __tmp__sum_balance0, __tmp__M_Ref_int_balance0;



procedure {:inline 1} balanceOf_Wallet__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, user_s101: Ref)
   returns (__ret_0_: int);
  modifies gas;



procedure {:inline 1} balanceOf_Wallet__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, user_s101: Ref)
   returns (__ret_0_: int);
  modifies gas;



procedure {:inline 1} FallbackDispatch__fail(from: Ref, to: Ref, amount: int);
  modifies __tmp__Balance, revert, gas, __tmp__sum_balance0, __tmp__M_Ref_int_balance0, __tmp__DType, __tmp__Alloc, __tmp__balance_ADDR, __tmp__Length, __tmp__now, __tmp__balance_Wallet;



procedure {:inline 1} Fallback_UnknownType__fail(from: Ref, to: Ref, amount: int);
  modifies __tmp__Balance, revert, gas, __tmp__sum_balance0, __tmp__M_Ref_int_balance0, __tmp__DType, __tmp__Alloc, __tmp__balance_ADDR, __tmp__Length, __tmp__now, __tmp__balance_Wallet;



procedure {:inline 1} send__fail(from: Ref, to: Ref, amount: int) returns (success: bool);
  modifies __tmp__Balance, __tmp__DType, __tmp__Alloc, __tmp__balance_ADDR, __tmp__M_Ref_int_balance0, __tmp__sum_balance0, __tmp__Length, __tmp__now, __tmp__balance_Wallet, revert, gas;



implementation FreshRefGenerator__fail() returns (newRef: Ref)
{
    havoc newRef;
    assume __tmp__Alloc[newRef] == false;
    __tmp__Alloc[newRef] := true;
    assume newRef != null;
}



implementation FreshRefGenerator__success() returns (newRef: Ref)
{
    havoc newRef;
    assume Alloc[newRef] == false;
    Alloc[newRef] := true;
    assume newRef != null;
}



implementation Wallet_Wallet_NoBaseCtor__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
  var __var_1: Ref;

    assume msgsender_MSG != null;
    __tmp__Balance[this] := 0;
    call __var_1 := FreshRefGenerator__fail();
    if (revert)
    {
        return;
    }

    __tmp__balance_Wallet[this] := __var_1;
    __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]] := zeroRefIntArr();
    __tmp__sum_balance0[__tmp__balance_Wallet[this]] := 0;
}



implementation Wallet_Wallet_NoBaseCtor__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
  var __var_1: Ref;

    assume msgsender_MSG != null;
    Balance[this] := 0;
    call __var_1 := FreshRefGenerator__success();
    if (revert)
    {
        return;
    }

    balance_Wallet[this] := __var_1;
    M_Ref_int_balance0[balance_Wallet[this]] := zeroRefIntArr();
    sum_balance0[balance_Wallet[this]] := 0;
}



implementation Wallet_Wallet__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
    call Wallet_Wallet_NoBaseCtor__fail(this, msgsender_MSG, msgvalue_MSG);
    if (revert)
    {
        return;
    }
}



implementation Wallet_Wallet__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
    call Wallet_Wallet_NoBaseCtor__success(this, msgsender_MSG, msgvalue_MSG);
    if (revert)
    {
        return;
    }
}



implementation deposit_Wallet__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
    assume __tmp__Balance[msgsender_MSG] >= msgvalue_MSG;
    __tmp__Balance[msgsender_MSG] := __tmp__Balance[msgsender_MSG] - msgvalue_MSG;
    __tmp__Balance[this] := __tmp__Balance[this] + msgvalue_MSG;
    gas := gas - 20327;
    assume __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG] >= 0;
    assume msgvalue_MSG >= 0;
    __tmp__sum_balance0[__tmp__balance_Wallet[this]] := __tmp__sum_balance0[__tmp__balance_Wallet[this]]
       - __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG];
    __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG] := __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG]
       + msgvalue_MSG;
    __tmp__sum_balance0[__tmp__balance_Wallet[this]] := __tmp__sum_balance0[__tmp__balance_Wallet[this]]
       + __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG];
}



implementation deposit_Wallet__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
    assume Balance[msgsender_MSG] >= msgvalue_MSG;
    Balance[msgsender_MSG] := Balance[msgsender_MSG] - msgvalue_MSG;
    Balance[this] := Balance[this] + msgvalue_MSG;
    gas := gas - 20327;
    assume M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG] >= 0;
    assume msgvalue_MSG >= 0;
    sum_balance0[balance_Wallet[this]] := sum_balance0[balance_Wallet[this]]
       - M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG];
    M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG] := M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG] + msgvalue_MSG;
    sum_balance0[balance_Wallet[this]] := sum_balance0[balance_Wallet[this]]
       + M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG];
}



implementation withdraw_Wallet__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, amt_s46: int)
{
  var __var_2: int;
  var __var_3: bool;

    gas := gas - 304;
    assume __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG] >= 0;
    assume amt_s46 >= 0;
    if (__tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG] >= amt_s46)
    {
        gas := gas - 20328;
        assume __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG] >= 0;
        assume amt_s46 >= 0;
        __tmp__sum_balance0[__tmp__balance_Wallet[this]] := __tmp__sum_balance0[__tmp__balance_Wallet[this]]
           - __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG];
        __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG] := __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG] - amt_s46;
        __tmp__sum_balance0[__tmp__balance_Wallet[this]] := __tmp__sum_balance0[__tmp__balance_Wallet[this]]
           + __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG];
        gas := gas - 35786;
        __var_2 := gas;
        if (gas > 2300)
        {
            gas := 2300;
        }

        __var_2 := __var_2 - gas;
        assume amt_s46 >= 0;
        call __var_3 := send__fail(this, msgsender_MSG, amt_s46);
        if (!__var_3)
        {
            revert := true;
            return;
        }

        gas := __var_2 + gas;
    }
}



implementation withdraw_Wallet__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, amt_s46: int)
{
  var __var_2: int;
  var __var_3: bool;

    gas := gas - 304;
    assume M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG] >= 0;
    assume amt_s46 >= 0;
    if (M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG] >= amt_s46)
    {
        gas := gas - 20328;
        assume M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG] >= 0;
        assume amt_s46 >= 0;
        sum_balance0[balance_Wallet[this]] := sum_balance0[balance_Wallet[this]]
           - M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG];
        M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG] := M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG] - amt_s46;
        sum_balance0[balance_Wallet[this]] := sum_balance0[balance_Wallet[this]]
           + M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG];
        gas := gas - 35786;
        __var_2 := gas;
        if (gas > 2300)
        {
            gas := 2300;
        }

        __var_2 := __var_2 - gas;
        assume amt_s46 >= 0;
        call __var_3 := send__success(this, msgsender_MSG, amt_s46);
        if (!__var_3)
        {
            revert := true;
            return;
        }

        gas := __var_2 + gas;
    }
}



implementation transfer_Wallet__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, to_s89: Ref, amt_s89: int)
{
  var __var_4: Ref;
  var __var_5: Ref;

    gas := gas - 89;
    __var_4 := null;
    __var_5 := this;
    if (!(to_s89 != null && to_s89 != this))
    {
        revert := true;
        return;
    }

    gas := gas - 304;
    assume __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG] >= 0;
    assume amt_s89 >= 0;
    if (__tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG] >= amt_s89)
    {
        gas := gas - 20328;
        assume __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG] >= 0;
        assume amt_s89 >= 0;
        __tmp__sum_balance0[__tmp__balance_Wallet[this]] := __tmp__sum_balance0[__tmp__balance_Wallet[this]]
           - __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG];
        __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG] := __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG] - amt_s89;
        __tmp__sum_balance0[__tmp__balance_Wallet[this]] := __tmp__sum_balance0[__tmp__balance_Wallet[this]]
           + __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][msgsender_MSG];
        gas := gas - 20323;
        assume __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][to_s89] >= 0;
        assume amt_s89 >= 0;
        __tmp__sum_balance0[__tmp__balance_Wallet[this]] := __tmp__sum_balance0[__tmp__balance_Wallet[this]]
           - __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][to_s89];
        __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][to_s89] := __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][to_s89] + amt_s89;
        __tmp__sum_balance0[__tmp__balance_Wallet[this]] := __tmp__sum_balance0[__tmp__balance_Wallet[this]]
           + __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][to_s89];
    }
}



implementation transfer_Wallet__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, to_s89: Ref, amt_s89: int)
{
  var __var_4: Ref;
  var __var_5: Ref;

    gas := gas - 89;
    __var_4 := null;
    __var_5 := this;
    if (!(to_s89 != null && to_s89 != this))
    {
        revert := true;
        return;
    }

    gas := gas - 304;
    assume M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG] >= 0;
    assume amt_s89 >= 0;
    if (M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG] >= amt_s89)
    {
        gas := gas - 20328;
        assume M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG] >= 0;
        assume amt_s89 >= 0;
        sum_balance0[balance_Wallet[this]] := sum_balance0[balance_Wallet[this]]
           - M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG];
        M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG] := M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG] - amt_s89;
        sum_balance0[balance_Wallet[this]] := sum_balance0[balance_Wallet[this]]
           + M_Ref_int_balance0[balance_Wallet[this]][msgsender_MSG];
        gas := gas - 20323;
        assume M_Ref_int_balance0[balance_Wallet[this]][to_s89] >= 0;
        assume amt_s89 >= 0;
        sum_balance0[balance_Wallet[this]] := sum_balance0[balance_Wallet[this]]
           - M_Ref_int_balance0[balance_Wallet[this]][to_s89];
        M_Ref_int_balance0[balance_Wallet[this]][to_s89] := M_Ref_int_balance0[balance_Wallet[this]][to_s89] + amt_s89;
        sum_balance0[balance_Wallet[this]] := sum_balance0[balance_Wallet[this]]
           + M_Ref_int_balance0[balance_Wallet[this]][to_s89];
    }
}



implementation balanceOf_Wallet__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, user_s101: Ref)
   returns (__ret_0_: int)
{
    gas := gas - 3;
    gas := gas - 304;
    assume __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][user_s101] >= 0;
    __ret_0_ := __tmp__M_Ref_int_balance0[__tmp__balance_Wallet[this]][user_s101];
    return;
}



implementation balanceOf_Wallet__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, user_s101: Ref)
   returns (__ret_0_: int)
{
    gas := gas - 3;
    gas := gas - 304;
    assume M_Ref_int_balance0[balance_Wallet[this]][user_s101] >= 0;
    __ret_0_ := M_Ref_int_balance0[balance_Wallet[this]][user_s101];
    return;
}



implementation FallbackDispatch__fail(from: Ref, to: Ref, amount: int)
{
    if (__tmp__DType[to] == Wallet)
    {
        assume amount == 0;
    }
    else
    {
        call Fallback_UnknownType__fail(from, to, amount);
        if (revert)
        {
            return;
        }
    }
}



implementation FallbackDispatch__success(from: Ref, to: Ref, amount: int)
{
    if (DType[to] == Wallet)
    {
        assume amount == 0;
    }
    else
    {
        call Fallback_UnknownType__success(from, to, amount);
        if (revert)
        {
            return;
        }
    }
}



implementation Fallback_UnknownType__fail(from: Ref, to: Ref, amount: int)
{
  var iterate: bool;
  var this: Ref;
  var msgsender_MSG: Ref;
  var msgvalue_MSG: int;
  var choice: int;
  var amt_s46: int;
  var to_s89: Ref;
  var amt_s89: int;
  var user_s101: Ref;
  var __ret_0_balanceOf: int;

    assume __tmp__Balance[from] >= amount;
    __tmp__Balance[from] := __tmp__Balance[from] - amount;
    __tmp__Balance[to] := __tmp__Balance[to] + amount;
    if (choice == 0)
    {
        revert := true;
        return;
    }

    if (gas < 21000)
    {
        return;
    }

    while (iterate && gas >= 21000)
    {
        havoc msgsender_MSG;
        havoc msgvalue_MSG;
        havoc choice;
        havoc amt_s46;
        havoc to_s89;
        havoc amt_s89;
        havoc user_s101;
        havoc __ret_0_balanceOf;
        havoc iterate;
        if (__tmp__DType[from] == Wallet)
        {
            if (choice == 4)
            {
                gas := gas - 21000;
                if (gas >= 0)
                {
                    assume msgvalue_MSG >= 0;
                    call deposit_Wallet__fail(from, to, msgvalue_MSG);
                    if (revert)
                    {
                        return;
                    }
                }
            }
            else if (choice == 3)
            {
                gas := gas - 21000;
                if (gas >= 0)
                {
                    assume msgvalue_MSG == 0;
                    call withdraw_Wallet__fail(from, to, msgvalue_MSG, amt_s46);
                    if (revert)
                    {
                        return;
                    }
                }
            }
            else if (choice == 2)
            {
                gas := gas - 21000;
                if (gas >= 0)
                {
                    assume msgvalue_MSG == 0;
                    call transfer_Wallet__fail(from, to, msgvalue_MSG, to_s89, amt_s89);
                    if (revert)
                    {
                        return;
                    }
                }
            }
            else if (choice == 1)
            {
                gas := gas - 21000;
                if (gas >= 0)
                {
                    assume msgvalue_MSG == 0;
                    call __ret_0_balanceOf := balanceOf_Wallet__fail(from, to, msgvalue_MSG, user_s101);
                    if (revert)
                    {
                        return;
                    }
                }
            }
        }
    }
}



implementation Fallback_UnknownType__success(from: Ref, to: Ref, amount: int)
{
  var iterate: bool;
  var this: Ref;
  var msgsender_MSG: Ref;
  var msgvalue_MSG: int;
  var choice: int;
  var amt_s46: int;
  var to_s89: Ref;
  var amt_s89: int;
  var user_s101: Ref;
  var __ret_0_balanceOf: int;

    assume Balance[from] >= amount;
    Balance[from] := Balance[from] - amount;
    Balance[to] := Balance[to] + amount;
    if (choice == 0)
    {
        revert := true;
        return;
    }

    if (gas < 21000)
    {
        return;
    }

    while (iterate && gas >= 21000)
    {
        havoc msgsender_MSG;
        havoc msgvalue_MSG;
        havoc choice;
        havoc amt_s46;
        havoc to_s89;
        havoc amt_s89;
        havoc user_s101;
        havoc __ret_0_balanceOf;
        havoc iterate;
        if (DType[from] == Wallet)
        {
            if (choice == 4)
            {
                gas := gas - 21000;
                if (gas >= 0)
                {
                    assume msgvalue_MSG >= 0;
                    call deposit_Wallet__success(from, to, msgvalue_MSG);
                    if (revert)
                    {
                        return;
                    }
                }
            }
            else if (choice == 3)
            {
                gas := gas - 21000;
                if (gas >= 0)
                {
                    assume msgvalue_MSG == 0;
                    call withdraw_Wallet__success(from, to, msgvalue_MSG, amt_s46);
                    if (revert)
                    {
                        return;
                    }
                }
            }
            else if (choice == 2)
            {
                gas := gas - 21000;
                if (gas >= 0)
                {
                    assume msgvalue_MSG == 0;
                    call transfer_Wallet__success(from, to, msgvalue_MSG, to_s89, amt_s89);
                    if (revert)
                    {
                        return;
                    }
                }
            }
            else if (choice == 1)
            {
                gas := gas - 21000;
                if (gas >= 0)
                {
                    assume msgvalue_MSG == 0;
                    call __ret_0_balanceOf := balanceOf_Wallet__success(from, to, msgvalue_MSG, user_s101);
                    if (revert)
                    {
                        return;
                    }
                }
            }
        }
    }
}



implementation send__fail(from: Ref, to: Ref, amount: int) returns (success: bool)
{
  var __exception: bool;
  var __snap___tmp__Balance: [Ref]int;
  var __snap___tmp__DType: [Ref]ContractName;
  var __snap___tmp__Alloc: [Ref]bool;
  var __snap___tmp__balance_ADDR: [Ref]int;
  var __snap___tmp__M_Ref_int_balance0: [Ref][Ref]int;
  var __snap___tmp__sum_balance0: [Ref]int;
  var __snap___tmp__Length: [Ref]int;
  var __snap___tmp__now: int;
  var __snap___tmp__balance_Wallet: [Ref]Ref;

    havoc __exception;
    if (__exception)
    {
        __snap___tmp__Balance := __tmp__Balance;
        __snap___tmp__DType := __tmp__DType;
        __snap___tmp__Alloc := __tmp__Alloc;
        __snap___tmp__balance_ADDR := __tmp__balance_ADDR;
        __snap___tmp__M_Ref_int_balance0 := __tmp__M_Ref_int_balance0;
        __snap___tmp__sum_balance0 := __tmp__sum_balance0;
        __snap___tmp__Length := __tmp__Length;
        __snap___tmp__now := __tmp__now;
        __snap___tmp__balance_Wallet := __tmp__balance_Wallet;
        if (__tmp__Balance[from] >= amount)
        {
            call FallbackDispatch__fail(from, to, amount);
        }

        success := false;
        assume revert || gas < 0;
        __tmp__Balance := __snap___tmp__Balance;
        __tmp__DType := __snap___tmp__DType;
        __tmp__Alloc := __snap___tmp__Alloc;
        __tmp__balance_ADDR := __snap___tmp__balance_ADDR;
        __tmp__M_Ref_int_balance0 := __snap___tmp__M_Ref_int_balance0;
        __tmp__sum_balance0 := __snap___tmp__sum_balance0;
        __tmp__Length := __snap___tmp__Length;
        __tmp__now := __snap___tmp__now;
        __tmp__balance_Wallet := __snap___tmp__balance_Wallet;
        revert := false;
    }
    else
    {
        if (__tmp__Balance[from] >= amount)
        {
            call FallbackDispatch__fail(from, to, amount);
            success := true;
        }
        else
        {
            success := false;
        }

        assume !revert && gas >= 0;
    }
}



implementation send__success(from: Ref, to: Ref, amount: int) returns (success: bool)
{
  var __exception: bool;

    havoc __exception;
    if (__exception)
    {
        __tmp__Balance := Balance;
        __tmp__DType := DType;
        __tmp__Alloc := Alloc;
        __tmp__balance_ADDR := balance_ADDR;
        __tmp__M_Ref_int_balance0 := M_Ref_int_balance0;
        __tmp__sum_balance0 := sum_balance0;
        __tmp__Length := Length;
        __tmp__now := now;
        __tmp__balance_Wallet := balance_Wallet;
        if (__tmp__Balance[from] >= amount)
        {
            call FallbackDispatch__fail(from, to, amount);
        }

        success := false;
        assume revert || gas < 0;
        revert := false;
    }
    else
    {
        if (Balance[from] >= amount)
        {
            call FallbackDispatch__success(from, to, amount);
            success := true;
        }
        else
        {
            success := false;
        }

        assume !revert && gas >= 0;
    }
}



implementation CorralChoice_Wallet(this: Ref)
{
  var msgsender_MSG: Ref;
  var msgvalue_MSG: int;
  var choice: int;
  var amt_s46: int;
  var to_s89: Ref;
  var amt_s89: int;
  var user_s101: Ref;
  var __ret_0_balanceOf: int;
  var tmpNow: int;

    havoc msgsender_MSG;
    havoc msgvalue_MSG;
    havoc choice;
    havoc amt_s46;
    havoc to_s89;
    havoc amt_s89;
    havoc user_s101;
    havoc __ret_0_balanceOf;
    havoc tmpNow;
    havoc gas;
    assume gas > 4000000 && gas <= 8000000;
    tmpNow := now;
    havoc now;
    assume now > tmpNow;
    assume msgsender_MSG != null;
    assume DType[msgsender_MSG] != Wallet;
    Alloc[msgsender_MSG] := true;
    if (choice == 4)
    {
        gas := gas - 21000;
        if (gas >= 0)
        {
            assume msgvalue_MSG >= 0;
            call deposit_Wallet(this, msgsender_MSG, msgvalue_MSG);
        }
    }
    else if (choice == 3)
    {
        gas := gas - 21000;
        if (gas >= 0)
        {
            assume msgvalue_MSG == 0;
            call withdraw_Wallet(this, msgsender_MSG, msgvalue_MSG, amt_s46);
        }
    }
    else if (choice == 2)
    {
        gas := gas - 21000;
        if (gas >= 0)
        {
            assume msgvalue_MSG == 0;
            call transfer_Wallet(this, msgsender_MSG, msgvalue_MSG, to_s89, amt_s89);
        }
    }
    else if (choice == 1)
    {
        gas := gas - 21000;
        if (gas >= 0)
        {
            assume msgvalue_MSG == 0;
            call __ret_0_balanceOf := balanceOf_Wallet(this, msgsender_MSG, msgvalue_MSG, user_s101);
        }
    }
}



implementation main()
{
  var this: Ref;
  var msgsender_MSG: Ref;
  var msgvalue_MSG: int;

    assume null == 0;
    call this := FreshRefGenerator__success();
    assume now >= 0;
    assume DType[this] == Wallet;
    gas := gas - 53000;
    call Wallet_Wallet__success(this, msgsender_MSG, msgvalue_MSG);
    assume !revert && gas >= 0;
    while (true)
    {
        call CorralChoice_Wallet(this);
    }
}


