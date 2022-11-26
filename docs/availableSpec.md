# 支持场景

> 理想情况下，一阶逻辑的表达能力也就是本规约的表达能力，换句话说，如果能够将意图抽象为一阶逻辑，则该意图是可验证的。但在实际情况中，由于规约书写者的能力，以及工具的支持范围，实际能验证的意图应该为上述所述内容的真子集。
>
> 因此，本节以使用者的视角，对smartLTL规约的表达能力和使用场景进行了阐述，基本囊括了工具能检测的情景。由于笔者的能力有限，下述内容仅代表个人的观点，很可能没有发挥出工具的全部作用，且不乏存在一些表述不当的地方，如您在阅读的过程中发现错误，不妨提一个issue或直接联系作者。

> 要理解规约什么意思，结合具体的函数是十分重要的，或者说，只有知道源码或者有预定义的源码结构，才能够写出合适的规约。因此本节并不会详细阐述每一条规约是怎么写出来的，仅表达其含义。

## 仅验证属性

> 概述：可以判断合约在运行过程中满足的属性，包括合约在运行过程中/函数被调用时/函数运行结束时，是否一直/在未来某时刻，满足某一性质。

形式：

```
// #LTLProperty: 验证属性内容
```

**1 全局**

例：拍卖中，合约账户的余额应大于或等于最高出价及所有要退回的钱。

```
// #LTLProperty: [](!finished(*, this.funded == false && sum_backers0[backers_Crowdfunding[this]] > Balance[this]))
```

> 由第一个例子可见，通常要正面检验一件事情可能不太容易时，我们会书写“某个事情不会发生”这种类型的话来进行验证。不太容易的一个重要原因来源于，对于智能合约而言，其函数的运行方式是通过发送交易调用，如果该函数都不会被执行，那整个句子自然是错的。当然，这样做不仅是为了方便书写，同时也是为了在意图不一致的情况下，更快找出反例。

**2 某个函数**

例：胜利方仅有可能是参与方A或B，不可能是其他任何人，并且获胜者的金额等于奖池的金额。

```
// #LTLProperty: [](!finished(send(from, to, amt), to != owner_RockPaperScissors[from] && to != player1_RockPaperScissors[from] && to != player2_RockPaperScissors[from] || amt != old(Balance[from])))
```

例：在`swapIn`函数启动时，参数` tokenIn`和`tokenOut`不应该相同。

```
// #LTLProperty: [](!started(addLiquidityPair.swapIn(tokenIn, tokenOut, msg.sender, to ,amountIn), tokenIn == tokenOut))
```

> 下述内容同理，即针对函数的约束可以指定为某一个具体函数，也可以用*表示全局。

## 验证属性及公平属性

> 概述：上文所说的仅验证属性，可以看作是公平属性为空（恒为真）时的情况。这种情况的含义即是，在满足公平属性的情况下，验证属性是否为真。

形式：

```
// #LTLFairness: 公平性属性
// #LTLProperty: 验证属性内容
```

例：已知`DosAuction.bid`函数会在`msg.value > this.currentBid`条件下启动，那么该函数会执行完毕。

```
// #LTLFairness: [](<>(started(DosAuction.bid, msg.value > this.currentBid)))
// #LTLProperty: [](<>(finished(DosAuction.bid)))
```

## 验证属性及变量

> 概述：在验证意图的过程中，可能会遇到需要用到变量来表示指定的元素，此时自由变量的作用便体现出来了。通常来说，在“对于某些事物（For some things）”这种表述情况下，变量就很可能会被使用的。

形式：

```
// #LTLVariables: 变量名:Type
// #LTLProperty: 验证属性内容
```

例：对于某个账户a，合约应该记录他转给该合约的所有钱。

```
// #LTLVariables: a:Ref
// #LTLProperty: [](!finished(*, this.pendingReturns[a] != fsum(Auction.Bid, 2, msg.sender == a) - fsum(send(from, to, amt), 2, to == a))) 
```

## 验证属性及公平属性及变量

> 概述：即在上述验证属性及变量的基础上，加上了公平性属性约束。

形式：

```
// #LTLVariables: 变量名:Ref
// #LTLFairness: 公平性属性
// #LTLProperty: 验证属性内容
```

关于这一部分，可参见[使用示例部分](README.md)。

例：如何出资人没有取走钱，那么合约中保留了出资人的出资记录。

```
// #LTLVariables: b:Ref,v:int
// #LTLFairness: [](!started(Crowdfunding.Claim, msg.sender == b))
// #LTLProperty: [](finished(Crowdfunding.Donate, msg.sender == b && msg.value == v && v != 0) ==> [](!finished(*, this.backers[b] != v))) 
```

例：竞拍完全结束之后，用户可以取回他之前竞拍时投入的所有资金。	

```
// #LTLVariables: user:Ref
// #LTLFairness: (<>(finished(ValidatorAuction.withdraw, (user == msg.sender))))
// #LTLProperty: []((finished(ValidatorAuction.closeAuction)) ==> (<>(finished(send(from, to, amt), (to == user && amt == fsum(ValidatorAuction.bid, 2, (user == msg.sender)))))))
```

