# IcyChecker安装指南

IcyChecker由两部分构成，第一部分为VeriSol的修改版，用于将Solidity转化为Boogie，另一部分为SmartPulse的修改版，用于对转化后的合约进行给定属性的验证。

## 依赖项

- Python 2
- [Java JDK (1.8)](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

+ [VeriSol](https://github.com/utopia-group/verisol)

> 建议使用对应的版本进行构建，以避免出现意外。如果你当前开发环境与这些依赖项冲突，建议在虚拟环境或docker中完成以下操作。

## 构建

在进行正式构建前，你可以先确保已经安装了Python2和JDK1.8，并将其添加至环境变量中，关于这一步此处不在此做过多说明，为了检查是否完成了安装，您可以通过`-version`命令来进行查看。当然，你也可以在完成VeriSol的构建之后再进行这一步。

### 修改版Verisol部分

如工具描述所言，该工具首先需要安装一个魔改版的[VeriSol](https://github.com/utopia-group/verisol)。VeriSol是微软的一个研究项目，旨在为智能合约建立一个形式化验证和分析系统原型。你可以按照以下步骤完成，也可以参考其完整的安装指南进行。

#### 1 从[.NET官网](https://dotnet.microsoft.com/download/dotnet-core/2.2#sdk-2.2.106)安装 .NET Core（version 2.2）

> .NET Core 2.2 SDK (v2.2.207) 的二进制包是可用的。

VeriSol在首次运行时会安装Solidity编译器（仅适用于Windows和Linux）、Z3定理证明器、Corral和Boogie，其中后两个作为dotnet CLI工具安装。

#### 2 从源代码安装工具

首先克隆本仓库

```
git clone https://github.com/VeriInplus/IcyChecker.git
```

进入到`verisol`目录下, 执行

```shell
dotnet build Sources\VeriSol.sln
```

将VeriSol安装到dotnet CLI的工具缓存中，这样就可以全局使用了（可选）:

```shell
dotnet tool install VeriSol --version 0.1.1-alpha --global --add-source %VERISOL_PATH%/nupkg/
```

VeriSol在下一步的工具中会被直接调用，因此建议将其设置为全局可用，这样做能够有效避免环境配置带来的麻烦。

如果安装过老版本，可能还需要卸载掉之前的版本，运行 `dotnet tool uninstall --global VeriSol`

>可能的报错：Couldn't find a valid ICU package installed on the system. Set the configuration flag System.Globalization.Invariant to true if you want to run with no globalization support. 你可能会遇到这个错误，这意味着你缺少libICU。
>
>注：为了减少工具所占空间，工具删除了Verisol自带的验证功能，如果你想重新使用，请删除`program.cs`中注释掉的`ExternalToolsManager`，并重新运行上述步骤。

至此，你已经完成了verisol部分的构建。事实上，如果你直接克隆的本仓库，那你已经可以正常运行了。

如果你想进一步手动配置后端验证部分，那么你可以参照下述方案进行。

### SmartPulse / Ultimate Automizer

Ultimate Automizer是一个软件验证器，它实现了一种基于自动机的方法来验证安全性和活性（safety and liveness）属性，[这篇博客]((https://zhuanlan.zhihu.com/p/127842902))关于此进行了更详细的介绍。

#### 从预编译好的二进制文件构建

1. 完成VeriSol的安装。
2. [在此处](https://github.com/utopia-group/SmartPulseTool/releases)下载你所需要的二进制文件。

> 建议您通过二进制文件直接构建。

>其他注意事项：运行时l2lba文件需要位于当前运行目录下。

3. 完成下载后即可直接运行，你可以进入SmartPulse文件夹，并输入`./SmartPulse.py`进行测试，参见下一节尝试具体的运行示例。

#### 从Scratch构建

如果你不愿使用预编译好的二进制文件，那么你还需要以下依赖项：

- Maven 3.0
- Eclipse IDE for RCP and RAP Developers 2019-09 or older

**构建指南**

1. 完成VeriSol的安装，并克隆[SmartPulseTool仓库](https://github.com/utopia-group/SmartPulseTool/tree/master).

2. 跟随[该步骤](https://github.com/ultimate-pa/ultimate/wiki/Installation/2979de9af052431d7923beeb8a77dacc23d5e528)构建Ultimate Automizer.

3. Run `createSmartPulse.sh`

完成后将其中的多有内容放置在`/smartpulse`路径下即可。

## 相关参数

修改后的工具仍保留了原SmartPulse的参数：

```
./IcyChecker.py [args] contract.sol contractName spec.spec 

Behavioral Models: 
  -modArith                   | Model integers using modular arithmetic rather than as mathematical integers 
  -instrumentGas              | Instrument gas usage using model (assumes model comes from solc) 
Adversary Models: 
  -noReentrancy               | Assume attacker cannot make reentrant calls 
  -singleCallback             | Assume attacker will only make one reentrant call 
  -powerfulAdversary          | Assume attacker can make arbitrary reentrant calls 
Harness Modifiers: 
  -tnxsOnFields               | Allow transactions to be issued to contracts in fields of main contract 
  -checkPrePost:<fn1,fn2,...> | Check pre/post conditions of the specified functions 
```

现在回到[使用示例](/?id=usage)看下一步吧~

