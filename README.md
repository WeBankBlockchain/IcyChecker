# IcyChecker
A Intention-to-Code consistency checker for  Smart Contracts.

## About The Project

本仓库记录了**中山大学InplusLab**与**微众银行Webank**关于智能合约意图一致性检测算法的主要内容，旨在利用形式化验证等方法验证编写合约与意图是否一致，这其中包含了功能性与非功能性的需求。该算法旨在追求最大程度的用户友好和自动化，能够让没有过多形式化验证经验的使用者也能顺利使用该工具。理想情况下，用户仅需要编写简单的规约即可自动完成智能合约的一致性验证。

## Getting Started

目前，本项目可通过一个配置好的docker容器或完全手动配置来实现，详见[快速上手](https://veriinplus.github.io/IcyChecker/#/?id=%e5%bf%ab%e9%80%9f%e4%b8%8a%e6%89%8b)。

> 未来，我们会进一步简化工具的安装方法，尽可能可能提供即插即用的使用方案。

## Usage

在使用之前，需要学习[规约概述](https://veriinplus.github.io/IcyChecker/#/spec)。

使用者只需要编写一个规约即可实现对智能合约源码的验证，因此工具的理想的验证形式为：

```
IcyChecker contractFile contractName spec
```

目前，我们提供了SmartPulse的使用说明，参见[使用文档](https://veriinplus.github.io/IcyChecker/)。该部分提供了两个在公链上极为常见的场景案例——众筹和竞拍，同时我们也提供了目前论文中出现过的合约数据集。

## TODO
- [x] 调研联盟链合约验证情形，研究规约的适用场景和范围
- [x] 研究规约的易写和易读性
- [x] 研究依赖工具的设计和实现
- [x] 完善文档和使用说明

## Built On

本项目基于现有的两个工具进行研究和开发，该部分内容遵守其相应的license：

* [VeriSol](https://github.com/utopia-group/verisol)
* [SmartPulseTool](https://github.com/utopia-group/SmartPulseTool/tree/master)

## License

遵循MIT License，详见 `LICENSE.txt` 。
## Reference

* [Formal Verification of Workflow Policies for Smart Contracts in Azure Blockchain.](https://doi.org/10.1007/978-3-030-41600-3_7)
* [SmartPulse: Automated Checking of Temporal Properties in Smart Contracts.](https://doi.org/10.1109/SP40001.2021.00085)
