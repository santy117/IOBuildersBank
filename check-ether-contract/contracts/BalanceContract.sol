pragma solidity ^0.5.0;

   
  contract BalanceContract {


    function checkEther( address recipient) public view returns(uint256){
        return recipient.balance / 1 ether;
    }
}