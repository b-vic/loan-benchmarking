# loan-benchmarking

Quick test to compare a for loop to streams approach when interating and accumulating results from a list.

Stream equivalent become quicker as the list size grows.  At 100,000 items the stream is faster than the loop:

## 3 Loan List
```
Benchmark                Mode  Cnt           Score           Error  Units
LoanSystem.testLoops     avgt    5          64.325 ±        16.008  ns/op
LoanSystem.testStreams   avgt    5         528.155 ±       107.181  ns/op
```
## 10,000 Loan List
```
Benchmark                Mode  Cnt           Score           Error  Units
LoanSystem.testLoops     avgt    5     1110454.246 ±     19290.638  ns/op
LoanSystem.testStreams   avgt    5     1607043.097 ±   2692775.313  ns/op
```
## 100,000 Loan List
```
Benchmark               Mode  Cnt         Score          Error  Units
LoanSystem.testLoops    avgt    5  25121729.503 ± 36048978.757  ns/op
LoanSystem.testStreams  avgt    5  22479319.530 ± 30537722.215  ns/op
```
