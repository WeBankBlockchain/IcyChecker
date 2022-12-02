pragma solidity ^0.5.0;

contract CounterUtil {
    struct Counter {
        int256 value;
    }

    struct Tool {
        Counter counter;
    }

    Tool tool;

    constructor() public {
        tool.counter.value = 1;
    }

    function current() external returns (int256) {
        return tool.counter.value;
    }

    function increment() external {
        tool.counter.value += 1;
    }

    function decrement() external {
        require(
            tool.counter.value > 0,
            "Counters decrement: subtraction overflow"
        );
        tool.counter.value = tool.counter.value - 1;
    }
}
