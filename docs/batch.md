
## 批量插入


https://mp.weixin.qq.com/s/N8z8QUsmcUD1QUWeMZHfkg

## 性能测试对比

1万条数据
```
saveBatch：6095 (11.102003642987249)
rewriteBatchedStatements：914 (1.6648451730418943)
mapper: 549 
```

10万条数据
```
saveBatch：54883 (18)
rewriteBatchedStatements：4859 (1.5952068286277086)
mapper：3046
```