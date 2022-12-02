pragma solidity ^0.4.25;

/**
 * @title Counters
 * @author SomeJoker
 * @dev 提供只能递增或递减1的计数器。这可以用于例如 跟踪铸造的ERC721的id或计数请求id。
 * 
 * 用法: `using Counters for Counters.Counter;`
 */
library Counters {
    struct Counter {
        uint256 _value; // default: 0
        uint256 real;
    }
	
	struct NextCounter {
		uint256 easy;
		Counter count;
	}
	
    function current(NextCounter storage samplecounter) internal view returns (uint256) {
        return samplecounter.count._value;
    }

    function increment(NextCounter storage samplecounter) internal {
        // 以1为增量，要达到2^256=1.1579209e+77,几乎不存在这样的场景,所以跳过了加法的安全检查,以节省gas
        samplecounter.count._value += 1;
    }

    function decrement(NextCounter storage samplecounter) internal {
        require(samplecounter.count._value >= 1, "Counters decrement: subtraction overflow");
        samplecounter.count._value = samplecounter.count._value - 1;
    }
}