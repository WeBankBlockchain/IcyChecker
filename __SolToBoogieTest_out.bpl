type Ref = int;
type ContractName = int;
const unique null: Ref;
const unique CounterUtil: ContractName;
const unique CounterUtil.Counter: ContractName;
const unique CounterUtil.Tool: ContractName;
function {:smtdefined "x"} ConstantToRef(x: int) returns (ret: Ref);
function BoogieRefToInt(x: Ref) returns (ret: int);
function {:bvbuiltin "mod"} modBpl(x: int, y: int) returns (ret: int);
function keccak256(x: int) returns (ret: int);
function abiEncodePacked1(x: int) returns (ret: int);
function _SumMapping_VeriSol(x: [Ref]int) returns (ret: int);
function abiEncodePacked2(x: int, y: int) returns (ret: int);
function abiEncodePacked1R(x: Ref) returns (ret: int);
function abiEncodePacked2R(x: Ref, y: int) returns (ret: int);
function {:smtdefined "((as const (Array Int Int)) 0)"} zeroRefIntArr() returns (ret: [Ref]int);
function {:smtdefined "((as const (Array Int Int)) 0)"} zeroIntIntArr() returns (ret: [int]int);
function {:smtdefined "((as const (Array Int Bool)) false)"} zeroRefBoolArr() returns (ret: [Ref]bool);
function {:smtdefined "((as const (Array Int Bool)) false)"} zeroIntBoolArr() returns (ret: [int]bool);
function {:smtdefined "((as const (Array Int Int)) 0)"} zeroRefRefArr() returns (ret: [Ref]Ref);
function {:smtdefined "((as const (Array Int Int)) 0)"} zeroIntRefArr() returns (ret: [int]Ref);
function nonlinearMul(x: int, y: int) returns (ret: int);
function nonlinearDiv(x: int, y: int) returns (ret: int);
function nonlinearPow(x: int, y: int) returns (ret: int);
function nonlinearMod(x: int, y: int) returns (ret: int);
var Balance: [Ref]int;
var DType: [Ref]ContractName;
var Alloc: [Ref]bool;
var balance_ADDR: [Ref]int;
var Length: [Ref]int;
var revert: bool;
var now: int;
procedure {:inline 1} FreshRefGenerator__success() returns (newRef: Ref);
modifies Alloc;
procedure {:inline 1} CounterUtil.Counter_ctor__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, value: int);
modifies value_CounterUtil.Counter;
procedure {:inline 1} CounterUtil.Tool_ctor__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, counter: Ref);
modifies counter_CounterUtil.Tool;
var value_CounterUtil.Counter: [Ref]int;
var counter_CounterUtil.Tool: [Ref]Ref;
var {:access "this.tool=tool_CounterUtil[this]"} tool_CounterUtil: [Ref]Ref;
procedure {:inline 1} CounterUtil_CounterUtil_NoBaseCtor__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
modifies Balance;
modifies tool_CounterUtil;
modifies value_CounterUtil.Counter;
modifies Alloc;
procedure {:constructor} {:public} {:inline 1} CounterUtil_CounterUtil(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
modifies revert;
modifies __tmp__Balance;
modifies __tmp__DType;
modifies __tmp__Alloc;
modifies __tmp__balance_ADDR;
modifies __tmp__Length;
modifies __tmp__now;
modifies __tmp__value_CounterUtil.Counter;
modifies __tmp__counter_CounterUtil.Tool;
modifies __tmp__tool_CounterUtil;
modifies Balance;
modifies tool_CounterUtil;
modifies value_CounterUtil.Counter;
modifies Alloc;
implementation CounterUtil_CounterUtil(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
var __exception: bool;
havoc __exception;
revert := false;
if (__exception) {
__tmp__Balance := Balance;
__tmp__DType := DType;
__tmp__Alloc := Alloc;
__tmp__balance_ADDR := balance_ADDR;
__tmp__Length := Length;
__tmp__now := now;
__tmp__value_CounterUtil.Counter := value_CounterUtil.Counter;
__tmp__counter_CounterUtil.Tool := counter_CounterUtil.Tool;
__tmp__tool_CounterUtil := tool_CounterUtil;
call CounterUtil_CounterUtil__fail(this, msgsender_MSG, msgvalue_MSG);
assume (revert);
} else {
call CounterUtil_CounterUtil__success(this, msgsender_MSG, msgvalue_MSG);
assume (!(revert));
}
}

procedure {:public} {:inline 1} current_CounterUtil(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int) returns (__ret_0_: int);
modifies revert;
modifies __tmp__Balance;
modifies __tmp__DType;
modifies __tmp__Alloc;
modifies __tmp__balance_ADDR;
modifies __tmp__Length;
modifies __tmp__now;
modifies __tmp__value_CounterUtil.Counter;
modifies __tmp__counter_CounterUtil.Tool;
modifies __tmp__tool_CounterUtil;
implementation current_CounterUtil(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int) returns (__ret_0_: int)
{
var __exception: bool;
havoc __exception;
revert := false;
if (__exception) {
__tmp__Balance := Balance;
__tmp__DType := DType;
__tmp__Alloc := Alloc;
__tmp__balance_ADDR := balance_ADDR;
__tmp__Length := Length;
__tmp__now := now;
__tmp__value_CounterUtil.Counter := value_CounterUtil.Counter;
__tmp__counter_CounterUtil.Tool := counter_CounterUtil.Tool;
__tmp__tool_CounterUtil := tool_CounterUtil;
call __ret_0_ := current_CounterUtil__fail(this, msgsender_MSG, msgvalue_MSG);
assume (revert);
} else {
call __ret_0_ := current_CounterUtil__success(this, msgsender_MSG, msgvalue_MSG);
assume (!(revert));
}
}

procedure {:public} {:inline 1} increment_CounterUtil(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
modifies revert;
modifies __tmp__Balance;
modifies __tmp__DType;
modifies __tmp__Alloc;
modifies __tmp__balance_ADDR;
modifies __tmp__Length;
modifies __tmp__now;
modifies __tmp__value_CounterUtil.Counter;
modifies __tmp__counter_CounterUtil.Tool;
modifies __tmp__tool_CounterUtil;
modifies value_CounterUtil.Counter;
implementation increment_CounterUtil(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
var __exception: bool;
havoc __exception;
revert := false;
if (__exception) {
__tmp__Balance := Balance;
__tmp__DType := DType;
__tmp__Alloc := Alloc;
__tmp__balance_ADDR := balance_ADDR;
__tmp__Length := Length;
__tmp__now := now;
__tmp__value_CounterUtil.Counter := value_CounterUtil.Counter;
__tmp__counter_CounterUtil.Tool := counter_CounterUtil.Tool;
__tmp__tool_CounterUtil := tool_CounterUtil;
call increment_CounterUtil__fail(this, msgsender_MSG, msgvalue_MSG);
assume (revert);
} else {
call increment_CounterUtil__success(this, msgsender_MSG, msgvalue_MSG);
assume (!(revert));
}
}

procedure {:public} {:inline 1} decrement_CounterUtil(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
modifies revert;
modifies __tmp__Balance;
modifies __tmp__DType;
modifies __tmp__Alloc;
modifies __tmp__balance_ADDR;
modifies __tmp__Length;
modifies __tmp__now;
modifies __tmp__value_CounterUtil.Counter;
modifies __tmp__counter_CounterUtil.Tool;
modifies __tmp__tool_CounterUtil;
modifies value_CounterUtil.Counter;
implementation decrement_CounterUtil(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
var __exception: bool;
havoc __exception;
revert := false;
if (__exception) {
__tmp__Balance := Balance;
__tmp__DType := DType;
__tmp__Alloc := Alloc;
__tmp__balance_ADDR := balance_ADDR;
__tmp__Length := Length;
__tmp__now := now;
__tmp__value_CounterUtil.Counter := value_CounterUtil.Counter;
__tmp__counter_CounterUtil.Tool := counter_CounterUtil.Tool;
__tmp__tool_CounterUtil := tool_CounterUtil;
call decrement_CounterUtil__fail(this, msgsender_MSG, msgvalue_MSG);
assume (revert);
} else {
call decrement_CounterUtil__success(this, msgsender_MSG, msgvalue_MSG);
assume (!(revert));
}
}

procedure {:inline 1} FallbackDispatch__success(from: Ref, to: Ref, amount: int);
procedure {:inline 1} Fallback_UnknownType__success(from: Ref, to: Ref, amount: int);
procedure {:inline 1} send__success(from: Ref, to: Ref, amount: int) returns (success: bool);
modifies __tmp__Balance;
modifies __tmp__DType;
modifies __tmp__Alloc;
modifies __tmp__balance_ADDR;
modifies __tmp__Length;
modifies __tmp__now;
modifies __tmp__value_CounterUtil.Counter;
modifies __tmp__counter_CounterUtil.Tool;
modifies __tmp__tool_CounterUtil;
modifies revert;
modifies Balance;
procedure CorralChoice_CounterUtil(this: Ref);
modifies now;
modifies Alloc;
modifies revert;
modifies __tmp__Balance;
modifies __tmp__DType;
modifies __tmp__Alloc;
modifies __tmp__balance_ADDR;
modifies __tmp__Length;
modifies __tmp__now;
modifies __tmp__value_CounterUtil.Counter;
modifies __tmp__counter_CounterUtil.Tool;
modifies __tmp__tool_CounterUtil;
modifies value_CounterUtil.Counter;
procedure main();
modifies Alloc;
modifies revert;
modifies __tmp__Balance;
modifies __tmp__DType;
modifies __tmp__Alloc;
modifies __tmp__balance_ADDR;
modifies __tmp__Length;
modifies __tmp__now;
modifies __tmp__value_CounterUtil.Counter;
modifies __tmp__counter_CounterUtil.Tool;
modifies __tmp__tool_CounterUtil;
modifies now;
modifies value_CounterUtil.Counter;
modifies Balance;
modifies tool_CounterUtil;
var __tmp__Balance: [Ref]int;
var __tmp__DType: [Ref]ContractName;
var __tmp__Alloc: [Ref]bool;
var __tmp__balance_ADDR: [Ref]int;
var __tmp__Length: [Ref]int;
var __tmp__now: int;
var __tmp__value_CounterUtil.Counter: [Ref]int;
var __tmp__counter_CounterUtil.Tool: [Ref]Ref;
var __tmp__tool_CounterUtil: [Ref]Ref;
procedure {:inline 1} FreshRefGenerator__fail() returns (newRef: Ref);
modifies __tmp__Alloc;
procedure {:inline 1} CounterUtil.Counter_ctor__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, value: int);
modifies __tmp__value_CounterUtil.Counter;
procedure {:inline 1} CounterUtil.Tool_ctor__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, counter: Ref);
modifies __tmp__counter_CounterUtil.Tool;
procedure {:inline 1} CounterUtil_CounterUtil_NoBaseCtor__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
modifies __tmp__Balance;
modifies __tmp__tool_CounterUtil;
modifies __tmp__value_CounterUtil.Counter;
modifies __tmp__Alloc;
procedure {:constructor} {:inline 1} CounterUtil_CounterUtil__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
modifies Balance;
modifies tool_CounterUtil;
modifies value_CounterUtil.Counter;
modifies Alloc;
procedure {:constructor} {:inline 1} CounterUtil_CounterUtil__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
modifies __tmp__Balance;
modifies __tmp__tool_CounterUtil;
modifies __tmp__value_CounterUtil.Counter;
modifies __tmp__Alloc;
procedure {:inline 1} current_CounterUtil__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int) returns (__ret_0_: int);
procedure {:inline 1} current_CounterUtil__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int) returns (__ret_0_: int);
procedure {:inline 1} increment_CounterUtil__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
modifies value_CounterUtil.Counter;
procedure {:inline 1} increment_CounterUtil__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
modifies __tmp__value_CounterUtil.Counter;
procedure {:inline 1} decrement_CounterUtil__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
modifies revert;
modifies value_CounterUtil.Counter;
procedure {:inline 1} decrement_CounterUtil__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int);
modifies revert;
modifies __tmp__value_CounterUtil.Counter;
procedure {:inline 1} FallbackDispatch__fail(from: Ref, to: Ref, amount: int);
procedure {:inline 1} Fallback_UnknownType__fail(from: Ref, to: Ref, amount: int);
procedure {:inline 1} send__fail(from: Ref, to: Ref, amount: int) returns (success: bool);
modifies __tmp__Balance;
modifies __tmp__DType;
modifies __tmp__Alloc;
modifies __tmp__balance_ADDR;
modifies __tmp__Length;
modifies __tmp__now;
modifies __tmp__value_CounterUtil.Counter;
modifies __tmp__counter_CounterUtil.Tool;
modifies __tmp__tool_CounterUtil;
modifies revert;
implementation FreshRefGenerator__fail() returns (newRef: Ref)
{
havoc newRef;
assume ((__tmp__Alloc[newRef]) == (false));
__tmp__Alloc[newRef] := true;
assume ((newRef) != (null));
}

implementation FreshRefGenerator__success() returns (newRef: Ref)
{
havoc newRef;
assume ((Alloc[newRef]) == (false));
Alloc[newRef] := true;
assume ((newRef) != (null));
}

implementation CounterUtil.Counter_ctor__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, value: int)
{
__tmp__value_CounterUtil.Counter[this] := value;
}

implementation CounterUtil.Counter_ctor__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, value: int)
{
value_CounterUtil.Counter[this] := value;
}

implementation CounterUtil.Tool_ctor__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, counter: Ref)
{
__tmp__counter_CounterUtil.Tool[this] := counter;
}

implementation CounterUtil.Tool_ctor__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int, counter: Ref)
{
counter_CounterUtil.Tool[this] := counter;
}

implementation CounterUtil_CounterUtil_NoBaseCtor__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
var __var_1: Ref;
// start of initialization
assume ((msgsender_MSG) != (null));
__tmp__Balance[this] := msgvalue_MSG;
// Make struct variables distinct for tool
call __var_1 := FreshRefGenerator__fail();
if (revert) {
return;
}
__tmp__tool_CounterUtil[this] := __var_1;
// end of initialization
__tmp__value_CounterUtil.Counter[__tmp__counter_CounterUtil.Tool[__tmp__tool_CounterUtil[this]]] := 1;
}

implementation CounterUtil_CounterUtil_NoBaseCtor__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
var __var_1: Ref;
// start of initialization
assume ((msgsender_MSG) != (null));
Balance[this] := msgvalue_MSG;
// Make struct variables distinct for tool
call __var_1 := FreshRefGenerator__success();
if (revert) {
return;
}
tool_CounterUtil[this] := __var_1;
// end of initialization
value_CounterUtil.Counter[counter_CounterUtil.Tool[tool_CounterUtil[this]]] := 1;
}

implementation CounterUtil_CounterUtil__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
var __var_1: Ref;
call CounterUtil_CounterUtil_NoBaseCtor__fail(this, msgsender_MSG, msgvalue_MSG);
if (revert) {
return;
}
}

implementation CounterUtil_CounterUtil__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
var __var_1: Ref;
call CounterUtil_CounterUtil_NoBaseCtor__success(this, msgsender_MSG, msgvalue_MSG);
if (revert) {
return;
}
}

implementation current_CounterUtil__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int) returns (__ret_0_: int)
{
__ret_0_ := __tmp__value_CounterUtil.Counter[__tmp__counter_CounterUtil.Tool[__tmp__tool_CounterUtil[this]]];
return;
}

implementation current_CounterUtil__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int) returns (__ret_0_: int)
{
__ret_0_ := value_CounterUtil.Counter[counter_CounterUtil.Tool[tool_CounterUtil[this]]];
return;
}

implementation increment_CounterUtil__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
__tmp__value_CounterUtil.Counter[__tmp__counter_CounterUtil.Tool[__tmp__tool_CounterUtil[this]]] := (__tmp__value_CounterUtil.Counter[__tmp__counter_CounterUtil.Tool[__tmp__tool_CounterUtil[this]]]) + (1);
}

implementation increment_CounterUtil__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
value_CounterUtil.Counter[counter_CounterUtil.Tool[tool_CounterUtil[this]]] := (value_CounterUtil.Counter[counter_CounterUtil.Tool[tool_CounterUtil[this]]]) + (1);
}

implementation decrement_CounterUtil__fail(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
if (!((__tmp__value_CounterUtil.Counter[__tmp__counter_CounterUtil.Tool[__tmp__tool_CounterUtil[this]]]) >= (0))) {
revert := true;
return;
}
__tmp__value_CounterUtil.Counter[__tmp__counter_CounterUtil.Tool[__tmp__tool_CounterUtil[this]]] := (__tmp__value_CounterUtil.Counter[__tmp__counter_CounterUtil.Tool[__tmp__tool_CounterUtil[this]]]) - (1);
}

implementation decrement_CounterUtil__success(this: Ref, msgsender_MSG: Ref, msgvalue_MSG: int)
{
if (!((value_CounterUtil.Counter[counter_CounterUtil.Tool[tool_CounterUtil[this]]]) >= (0))) {
revert := true;
return;
}
value_CounterUtil.Counter[counter_CounterUtil.Tool[tool_CounterUtil[this]]] := (value_CounterUtil.Counter[counter_CounterUtil.Tool[tool_CounterUtil[this]]]) - (1);
}

implementation FallbackDispatch__fail(from: Ref, to: Ref, amount: int)
{
if ((__tmp__DType[to]) == (CounterUtil)) {
assume ((amount) == (0));
} else {
call Fallback_UnknownType__fail(from, to, amount);
if (revert) {
return;
}
}
}

implementation FallbackDispatch__success(from: Ref, to: Ref, amount: int)
{
if ((DType[to]) == (CounterUtil)) {
assume ((amount) == (0));
} else {
call Fallback_UnknownType__success(from, to, amount);
if (revert) {
return;
}
}
}

implementation Fallback_UnknownType__fail(from: Ref, to: Ref, amount: int)
{
}

implementation Fallback_UnknownType__success(from: Ref, to: Ref, amount: int)
{
}

implementation send__fail(from: Ref, to: Ref, amount: int) returns (success: bool)
{
var __exception: bool;
var __snap___tmp__Balance: [Ref]int;
var __snap___tmp__DType: [Ref]ContractName;
var __snap___tmp__Alloc: [Ref]bool;
var __snap___tmp__balance_ADDR: [Ref]int;
var __snap___tmp__Length: [Ref]int;
var __snap___tmp__now: int;
var __snap___tmp__value_CounterUtil.Counter: [Ref]int;
var __snap___tmp__counter_CounterUtil.Tool: [Ref]Ref;
var __snap___tmp__tool_CounterUtil: [Ref]Ref;
havoc __exception;
if (__exception) {
__snap___tmp__Balance := __tmp__Balance;
__snap___tmp__DType := __tmp__DType;
__snap___tmp__Alloc := __tmp__Alloc;
__snap___tmp__balance_ADDR := __tmp__balance_ADDR;
__snap___tmp__Length := __tmp__Length;
__snap___tmp__now := __tmp__now;
__snap___tmp__value_CounterUtil.Counter := __tmp__value_CounterUtil.Counter;
__snap___tmp__counter_CounterUtil.Tool := __tmp__counter_CounterUtil.Tool;
__snap___tmp__tool_CounterUtil := __tmp__tool_CounterUtil;
if ((__tmp__Balance[from]) >= (amount)) {
// ---- Logic for payable function START 
__tmp__Balance[from] := (__tmp__Balance[from]) - (amount);
__tmp__Balance[to] := (__tmp__Balance[to]) + (amount);
// ---- Logic for payable function END 
call FallbackDispatch__fail(from, to, amount);
}
success := false;
assume (revert);
__tmp__Balance := __snap___tmp__Balance;
__tmp__DType := __snap___tmp__DType;
__tmp__Alloc := __snap___tmp__Alloc;
__tmp__balance_ADDR := __snap___tmp__balance_ADDR;
__tmp__Length := __snap___tmp__Length;
__tmp__now := __snap___tmp__now;
__tmp__value_CounterUtil.Counter := __snap___tmp__value_CounterUtil.Counter;
__tmp__counter_CounterUtil.Tool := __snap___tmp__counter_CounterUtil.Tool;
__tmp__tool_CounterUtil := __snap___tmp__tool_CounterUtil;
revert := false;
} else {
if ((__tmp__Balance[from]) >= (amount)) {
// ---- Logic for payable function START 
__tmp__Balance[from] := (__tmp__Balance[from]) - (amount);
__tmp__Balance[to] := (__tmp__Balance[to]) + (amount);
// ---- Logic for payable function END 
call FallbackDispatch__fail(from, to, amount);
success := true;
} else {
success := false;
}
assume (!(revert));
}
}

implementation send__success(from: Ref, to: Ref, amount: int) returns (success: bool)
{
var __exception: bool;
havoc __exception;
if (__exception) {
__tmp__Balance := Balance;
__tmp__DType := DType;
__tmp__Alloc := Alloc;
__tmp__balance_ADDR := balance_ADDR;
__tmp__Length := Length;
__tmp__now := now;
__tmp__value_CounterUtil.Counter := value_CounterUtil.Counter;
__tmp__counter_CounterUtil.Tool := counter_CounterUtil.Tool;
__tmp__tool_CounterUtil := tool_CounterUtil;
if ((__tmp__Balance[from]) >= (amount)) {
// ---- Logic for payable function START 
__tmp__Balance[from] := (__tmp__Balance[from]) - (amount);
__tmp__Balance[to] := (__tmp__Balance[to]) + (amount);
// ---- Logic for payable function END 
call FallbackDispatch__fail(from, to, amount);
}
success := false;
assume (revert);
revert := false;
} else {
if ((Balance[from]) >= (amount)) {
// ---- Logic for payable function START 
Balance[from] := (Balance[from]) - (amount);
Balance[to] := (Balance[to]) + (amount);
// ---- Logic for payable function END 
call FallbackDispatch__success(from, to, amount);
success := true;
} else {
success := false;
}
assume (!(revert));
}
}

implementation CorralChoice_CounterUtil(this: Ref)
{
var msgsender_MSG: Ref;
var msgvalue_MSG: int;
var choice: int;
var __ret_0_current: int;
var tmpNow: int;
havoc msgsender_MSG;
havoc msgvalue_MSG;
havoc choice;
havoc __ret_0_current;
havoc tmpNow;
tmpNow := now;
havoc now;
assume ((now) > (tmpNow));
assume ((msgsender_MSG) != (null));
assume ((DType[msgsender_MSG]) != (CounterUtil));
Alloc[msgsender_MSG] := true;
if ((choice) == (3)) {
assume ((msgvalue_MSG) == (0));
call __ret_0_current := current_CounterUtil(this, msgsender_MSG, msgvalue_MSG);
} else if ((choice) == (2)) {
assume ((msgvalue_MSG) == (0));
call increment_CounterUtil(this, msgsender_MSG, msgvalue_MSG);
} else if ((choice) == (1)) {
assume ((msgvalue_MSG) == (0));
call decrement_CounterUtil(this, msgsender_MSG, msgvalue_MSG);
}
}

implementation main()
{
var this: Ref;
var msgsender_MSG: Ref;
var msgvalue_MSG: int;
assume ((null) == (0));
call this := FreshRefGenerator__success();
assume ((now) >= (0));
assume ((DType[this]) == (CounterUtil));
assume ((msgvalue_MSG) == (0));
call CounterUtil_CounterUtil(this, msgsender_MSG, msgvalue_MSG);
assume (!(revert));
while (true)
{
call CorralChoice_CounterUtil(this);
}
}


// #LTLProperty: ![](finished(*, value_CounterUtil.Counter[counter_CounterUtil.Tool[tool_CounterUtil[this]]] < 0))
