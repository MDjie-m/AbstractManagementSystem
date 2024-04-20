import JSEncrypt from 'jsencrypt/bin/jsencrypt.min'

// 密钥对生成 http://web.chacuo.net/netrsakeypair

const publicKey = 'MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMT82IgjtSwuO9PVMFA949HiUOJBzmmp' +
'BlYBP/DCVzBHXgG9qNOim4gSyfT5ZXKTHpMqzxoyRfmAKJXG7iNkUh8CAwEAAQ=='

const privateKey = 'MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAxPzYiCO1LC4709Uw\n' +
  'UD3j0eJQ4kHOaakGVgE/8MJXMEdeAb2o06KbiBLJ9PllcpMekyrPGjJF+YAolcbu\n' +
  ' I2RSHwIDAQABAkEAj7UyNqduLawRA2o9E90CzgSAI4AEmPqxX58LyZXNy66NPFab\n' +
  'qgY0GDEXjQ+PAqQpQ5SqPOXMXPmkVs003EG4wQIhAOXn7muFbX7U89p393Ut2Aro\n' +
  '2rhOZuLIJvaDOoeeUwX/AiEA21hzoXj38q50xgXcSpB1FZre/9gSbFFiRQeyjXBF\n' +
  '8+ECIBsv8Cva3XXwLN4a7bJ3t2Ki4qqE6vnKfYENR7Cd8md1AiBXzW5sArpuILuJ\n' +
  '0Sq/hlf4EAiYLWwig+lsbIpi58B4wQIgJpWAF2oFbS7o3efoMP26vAh058V2CE1F\n' +
  '7vi+sGVOyF8='

// 加密
export function encrypt(txt) {
  const encryptor = new JSEncrypt()
  encryptor.setPublicKey(publicKey) // 设置公钥
  return encryptor.encrypt(txt) // 对数据进行加密
}

// 解密
export function decrypt(txt) {
  const encryptor = new JSEncrypt()
  encryptor.setPrivateKey(privateKey) // 设置私钥
  return encryptor.decrypt(txt) // 对数据进行解密
}

