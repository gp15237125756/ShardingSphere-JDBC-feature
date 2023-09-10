## Sharding-JDBC
该项目使用springboot配置方式，依赖sharding-jdbc中间件实现分库分表操作

## TestCase说明
 1. `BroadcastTableTest`表示广播表测试用例,在application.properties中配置profile=03
 2. `DatabaseTableShardingTests`表示分库分表测试用例,在application.properties中配置profile=02
 3. `ReadWriteTest`表示读写分离测试用例,在application.properties中配置profile=04
 4. `TableShardingTests`表示不分库只分表测试用例,在application.properties中配置profile=01

## 分片算法
 1. 标准分片算法
 2. Hint分片算法


## 相关文档
 https://www.yuque.com/ynigapn/igdep1/oxd9bkex2lv7zcib/edit