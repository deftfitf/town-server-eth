// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "truffle/Assert.sol";
import "truffle/DeployedAddresses.sol";
import "../contracts/Town.sol";

contract TownTest {
    event Log(string message);

    Town town = Town(DeployedAddresses.Town());

    function testMintLandSuccessfully() public {
        Assert.equal(town.mintLand(0, 0), 0, "town land can be minted");
        Assert.equal(town.mintLand(0, 1), 1, "town land can be minted");
        Assert.equal(town.mintLand(1, 0), 16, "town land can be minted");
        Assert.equal(town.mintLand(1, 1), 16 + 1, "town land can be minted");
        Assert.equal(town.mintLand(15, 14), 15 * 16 + 14, "town land can be minted");
        Assert.equal(town.mintLand(15, 15), 15 * 16 + 15, "town land can be minted");
    }

    function testGetLands() public {
        uint256[] memory fields = town.getLands();
        Assert.equal(fields.length, 16 * 16, "returns array fields which has 16 x 16 elements");

        Assert.notEqual(fields[0], 0, "minted land's value won't be 0");
        Assert.notEqual(fields[16 + 1], 0, "minted land's value won't be 0");
        Assert.equal(fields[16 + 2], 0, "not minted land's value must be 0");
        Assert.notEqual(fields[16 * 15 + 15], 0, "minted land's value won't be 0");
    }
}
