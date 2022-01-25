// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

import "@openzeppelin/contracts/token/ERC721/ERC721.sol";

contract Town is ERC721 {
    uint constant X = 16;
    uint constant Y = 16;
    Land[X][Y] private field;

    struct Land {
        address owner;
        uint256 descriptor;
    }

    constructor() ERC721("TownLand", "TLND") {
    }

    function mintLand(uint x, uint y) external returns (uint) {
        require(x >= 0 && x < X && y >= 0 && y < Y);
        require(field[y][x].descriptor == 0);

        uint landId = _getLandId(x, y);
        uint256 descriptor = _issueDescriptor(landId);
        field[y][x] = Land(msg.sender, descriptor);
        _mint(msg.sender, landId);

        return landId;
    }

    function getLands() external view returns (uint[] memory) {
        uint[] memory descriptors = new uint[](X * Y);

        for (uint y = 0; y < Y; y++) {
            for (uint x = 0; x < X; x++) {
                descriptors[_getLandId(y, x)] = field[y][x].descriptor;
            }
        }

        return descriptors;
    }

    function _issueDescriptor(uint landId) private view returns (uint256) {
        return uint256(keccak256(abi.encode(
                block.number, block.timestamp, msg.sender, landId)));
    }

    function _getLandId(uint x, uint y) private pure returns (uint) {
        return x * X + y;
    }

}
