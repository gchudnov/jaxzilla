## JsonDriver

Json Parser is built with [JavaCC](https://javacc.github.io/javacc/), a parser generator for Java applications.

## Installation

```bash
export JAVACC_VERSION=7.0.9

cd ~
mkdir -p ~/opt/javacc/target

wget "https://github.com/javacc/javacc/archive/javacc-${JAVACC_VERSION}.tar.gz"
wget "https://repo1.maven.org/maven2/net/java/dev/javacc/javacc/${JAVACC_VERSION}/javacc-${JAVACC_VERSION}.jar"

# extract

cp ./javacc-javacc-${JAVACC_VERSION}/scripts/ opt/javacc/
cp ./javacc-${JAVACC_VERSION}.jar ~/opt/javacc/target/

# edit .bashrc, add:
# javacc
export PATH=$PATH:$HOME/opt/javacc/scripts/

```

## Building

```bash
cd src/main/java/com/github/gchudnov/jaxzilla/io/
javacc JsonDriver.jj
```
