# 帮助文档

## 端口
项目端口：`8091`

本地访问前缀：`http:localhost:8091`

## 控制器：TestController

### 接口介绍：

- `/rsa64/getKeyPair`：获取一对公私钥
- `/rsa64/CodeBase64`：对内容进行加密，参数：需要加密的内容、公钥
- `/rsa64/Base64Code`：对密文进行解密，参数：密文、私钥

<br/>

#### 1、/rsa64/getKeyPair
**备注**：获取一对密钥

**请求类型**：GET

**参数**：无

**结果**：Map<String,String>
- key：privateKey
  - value：私钥
- key：publicKey
  - value：公钥

<br/>

#### 2、/rsa64/CodeBase64
**备注**：加密文本

**请求类型**：POST

**body参数**：
- content：需要加密的内容
- key：公钥

**结果**：String，加密后的密文

<br/>

#### 3、/rsa64/Base64Code
**备注**：将密文解密，注意解密的私钥和加密文本的公钥必须是一对

**请求类型**：POST

**body参数**：
- content：密文
- key：私钥

**结果**：String，解密后的明文

<br/>

### 注意

- 如果加密时遇到异常：`DER input, Integer tag error`，说明密钥不对，需要更换密钥；
- 如果加密解密不对，报异常，注意再生成的密钥中有没有自带`\r\n`换行符，本项目里在返回密钥前已经去掉了
