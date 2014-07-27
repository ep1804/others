# Memo

Some basic settings

## Ubuntu

Download Ubuntu image for Vmware

* e.g. http://www.traffictool.net/vmware/ubuntu1404t.html

Update it using 'Software Updater'

Resolve desktop duplication problem of Ubuntu on Vmware

* A work-around is roll back to previous kernel.
	1. [Boot with an older kernel.](http://askubuntu.com/questions/82140/how-can-i-boot-with-an-older-kernel-version)
	2. [Remove new kernel versions after downgrading.](http://askubuntu.com/questions/106031/how-can-i-remove-new-kernel-versions-after-downgrading)

Turn off Large Text

* Settings -> Universal Access

Locale & Time Zone

* Settings -> Language Support 
* Settings -> Text Entry 
* Settings -> Time & Date


## VI

Install vi fully

```
sudo apt-get install vim
```

Install Vundle

* https://github.com/gmarik/Vundle.vim#quick-start

Edit `.vimrc`, for example, 

```
set fileencodings=utf-8
set hlsearch
set tabstop=2
set shiftwidth=2
...

```


## Git

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


## Bash

Edit `.bashrc`, for example,

```
# custom aliases
alias ll='ls -alF'
alias la='ls -A'
alias l='ls -CF'
alias cp='cp -i'
alias mv='mv -i'
alias rm='rm -i'
alias eclipse='/home/user/bin/eclipse/eclipse-standard-luna/eclipse'

# custom environment variables 
export JAVA_HOME="/opt/jdk/jdk1.7.0_65"
export M2_HOME="/opt/apache-maven/apache-maven-3.2.2"
export M2="$M2_HOME/bin"
export MAVEN_OPTS="-Dfile.encoding=UTF8"
export NODE_BIN="/opt/node/node-v0.10.29-linux-x86/bin"
export VERTX_BIN="/opt/vertx/vert.x-2.1.2/bin"
export PATH="$VERTX_BIN:$NODE_BIN:$JAVA_HOME/bin:$M2:.:$PATH"
export LS_COLORS="no=00:fi=00:di=01;34:ln=01;36:pi=40;33:so=01;35:bd=40;33;01:cd=40;33;01:or=01;05;37;41:mi=01;05;37;41:ex=01;32:*.cmd=01;32:*.exe=01;32:*.com=01;32:*.btm=01;32:*.bat=01;32:*.sh=01;32:*.csh=01;32:*.tar=01;31:*.tgz=01;31:*.arj=01;31:*.taz=01;31:*.lzh=01;31:*.zip=01;31:*.z=01;31:*.Z=01;31:*.gz=01;31:*.bz2=01;31:*.bz=01;31:*.tz=01;31:*.rpm=01;31:*.cpio=01;31:*.jpg=01;35:*.gif=01;35:*.bmp=01;35:*.xbm=01;35:*.xpm=01;35:*.png=01;35:*.tif=01;35:"
export LS_COLORS="$LS_COLORS:*.c=00;32:*.cc=00;32:*.cpp=00;32:*.py=00;32:*.java=00;32:*.class=00;33:*.jar=00;33:*.tar=00;31:*.gz=00;31:*.zip=00;31:"
```


## Some tools

Installed in `/opt` directory, for example, 

```
user@uvm:/opt$ tree -L 2
.
├── apache-maven
│   └── apache-maven-3.2.2
├── jdk
│   └── jdk1.7.0_65
├── node
│   └── node-v0.10.29-linux-x86
├── packages
│   ├── esl-erlang_17.1-1~ubuntu~trusty_i386.deb
│   └── rabbitmq-server_3.3.4-1_all.deb
└── vertx
    └── vert.x-2.1.2
```
