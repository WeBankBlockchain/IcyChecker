pragma solidity ^0.5.0;


contract Auction {
  

  mapping(address => uint) parti;

  address payable currentFrontrunner;
  uint currentBid;

  function bid() payable public {
    require(msg.value > currentBid);

    if (currentFrontrunner != address(0)) {
      currentFrontrunner.transfer(currentBid);
    }

    currentFrontrunner = msg.sender;
    currentBid         = msg.value;
  
    parti[currentFrontrunner] = currentBid;
  }
}
