Memo
----

### Download Ubuntu image for Vmware

e.g. http://www.traffictool.net/vmware/ubuntu1404t.html


### Resolve desktop duplication problem of Ubuntu on Vmware

Workaround: Roll back to previous kernel

1. [Boot with an older kernel.](http://askubuntu.com/questions/82140/how-can-i-boot-with-an-older-kernel-version)
2. [Remove new kernel versions after downgrading.](http://askubuntu.com/questions/106031/how-can-i-remove-new-kernel-versions-after-downgrading)

### VI

Install vi fully

```
sudo apt-get install vim
```

Install Vundle

* https://github.com/gmarik/Vundle.vim#quick-start

Edit .vimrc, for example, 

```
set fileencodings=utf-8
set hlsearch
set tabstop=2
set shiftwidth=2
...

```

### Bash

Edit .bashrc, for example, 

```
alias ls='ls -F --color=auto'
alias ll='ls -alF'
alias la='ls -A'
alias l='ls -CF'
alias cp='cp -i'
alias mv='mv -i'
alias rm='rm -i'
```

### LS_COLORS for some files

Edit .dircolors, for example,
```
.c 00;32
.cc 00;32
.cpp 00;32
.py 00;32
.java 00;32
.class 00;33
.jar 00;33
.tar 00;31
.gz 00;31
.zip 00;31
```

### Git

Install git

```
sudo apt-get install vim
```

Set some global variables

```
git config --global user.name "..."
git config --global user.email "..."
git config --global color.ui auto
```
