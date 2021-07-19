
server setup

# java, scala, sbt, maven

#### as non-superuser `~/opt`

```
# https://adoptopenjdk.net/
tar zxvf jdkxxx.tar.gz

wget https://downloads.lightbend.com/scala/2.11.12/scala-2.11.12.tgz
tar -zxvf scala-2.11.12.tgz

wget https://piccolo.link/sbt-1.2.8.tgz
tar zxvf sbt-1.2.8.tgz

wget http://apache.mirror.cdnetworks.com/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
tar -zxvf apache-maven-3.6.3-bin.tar.gz
```

#### as non-superuser `~/.bashrc`

```
SETUP_DIR=/.../soonmok/setup
chmod 600 $SETUP_DIR/id_rsa_soonmok
export GIT_SSH_COMMAND="ssh -i $SETUP_DIR/id_rsa_soonmok -F /dev/null"

git config --global user.email "soonmok.kwon@...com"
git config --global user.name "Soonmok Kwon"

conda activate py2

export PS1='\[\e[36m\]\u\[\e[0m\]@\[\e[36m\]\H \[\e[36m\]\W \[\e[33m\]$\[\e[0m\] '

export LANG=en_US.UTF-8
export JAVA_HOME=/.../opt/jdk1.8.0_112
export SCALA_HOME=/.../opt/scala-2.11.12

export PATH=$JAVA_HOME/bin:$PATH
export PATH=$SCALA_HOME/bin:$PATH
export PATH=/.../opt/sbt/bin:$PATH
export PATH=/.../opt/apache-maven-3.6.3/bin:$PATH

java -version
scala -version
python -V
pip -V
```

# node, http-server, raml2html
 
#### as superuser

```
sudo yum install nodejs
sudo npm install http-server -g
sudo npm i -g raml2html
```

# git 2.x

#### as superuser

```
sudo yum install http://opensource.wandisco.com/centos/7/git/x86_64/wandisco-git-release-7-2.noarch.rpm
sudo yum install git
```

# bash-git-prompt

#### as non-superuser

After following git-setup and custom-theme-configuration in:

https://github.com/magicmonty/bash-git-prompt

Add following lines in `.bashrc`:

```
GIT_PROMPT_ONLY_IN_REPO=1
GIT_PROMPT_THEME=Custom
GIT_PROMPT_THEME_FILE=/.../.git-prompt-colors.sh
source ~/.bash-git-prompt/gitprompt.sh
```

in `.git-prompt-colors.sh`

```
# This theme for gitprompt.sh is optimized for the "Solarized Dark" and "Solarized Light" color schemes
# based on "Solarized Extravagant", with user@host on the second line and some things removed.

function override_git_prompt_colors() {
  GIT_PROMPT_THEME_NAME='Solarized UserHost'
  GIT_PROMPT_STAGED="${Yellow}● "
  GIT_PROMPT_UNTRACKED="${Cyan}… "
  GIT_PROMPT_STASHED="${BoldMagenta}⚑ "
  GIT_PROMPT_CLEAN="${Green}✔ "
  GIT_PROMPT_COMMAND_OK="${Green}✔ "
  GIT_PROMPT_COMMAND_FAIL="${Red}✘ "

  GIT_PROMPT_START_USER="_LAST_COMMAND_INDICATOR_${Cyan}\\u@\\h ${Yellow}${PathShort}"
  GIT_PROMPT_START_ROOT="${GIT_PROMPT_START_USER}"
  GIT_PROMPT_END_USER="\n${White}${Time12a} $ ${ResetColor}"
  GIT_PROMPT_END_ROOT="\n${White}${Time12a} #️ ${ResetColor}"

  GIT_PROMPT_LEADING_SPACE=1
  GIT_PROMPT_PREFIX="${Cyan}("
  GIT_PROMPT_SUFFIX="${Cyan})" 
  GIT_PROMPT_SYMBOLS_NO_REMOTE_TRACKING="✭"
}

reload_git_prompt_colors 'Solarized UserHost'
```

# bash-git-prompt

#### as non-superuser
