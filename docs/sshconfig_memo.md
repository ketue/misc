* 配置多个git账户
```
#config github
Host git.gewara.cn   #host 这里和hostname保持一致
    HostName git.gewara.cn
    IdentityFile /Users/monical/.ssh/id_rsa_gw   # 路径注意win和mac的区别，支持linux的～
    PreferredAuthentications publickey
    User git    # 此处必须用git作为用户名

#config monical1.github.io
Host github.com
    HostName github.com
    IdentityFile /Users/monical/resources/secrets/monical1.github.io
    PreferredAuthentications publickey
    User git

```

* ssh测试
````
ssh -v git@github.com
ssh -v git@git.gewara.cn

````

