Memo
----

### Download Ubuntu image for Vmware

e.g. http://www.traffictool.net/vmware/ubuntu1404t.html


### Resolve desktop duplication problem of Ubuntu on Vmware

Workaround: Roll back to previous kernel

1. [Boot with an older kernel.](http://askubuntu.com/questions/82140/how-can-i-boot-with-an-older-kernel-version)
2. [Remove new kernel versions after downgrading.](http://askubuntu.com/questions/106031/how-can-i-remove-new-kernel-versions-after-downgrading)

### VI

1. Install vi fully

```
sudo apt-get install vim
```

2. Install Vundle

* https://github.com/gmarik/Vundle.vim#quick-start

3. Edit .vimrc

For example, 

```
set fileencodings=utf-8
set hlsearch
set tabstop=2
set shiftwidth=2
...

```

### Edit .bashrc

For example, 

```
alias ll='ls -alF'
alias la='ls -A'
alias l='ls -CF'

alias cp='cp -i'
alias mv='mv -i'
alias rm='rm -i'
```
