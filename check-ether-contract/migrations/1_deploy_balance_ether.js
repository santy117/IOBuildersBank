var Balance = artifacts.require("BalanceContract");

module.exports = function(deployer){
    deployer.deploy(Balance);
}