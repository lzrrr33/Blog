@[toc]
# 后端开发技能学习(二) shell基础学习
### 环境搭建
1.linux下可以直接执行shell
2.windows下需要安装虚拟服务软件，如果你看了上一篇博文(https://blog.csdn.net/lzr_ps/article/details/108312144)
，那么你可以直接用gitbash来执行shell话不多说，先来写个hello world：
```
$ vi test.sh
```
写入
```
#!/bin/bash -v
# test.sh
echo "Hello, World"
```
有三种方式执行：
```
$ bash test.sh
Hello, World

$ source test.sh
Hello, World

$ . test.sh
Hello, World
```
### 语法
##### 变量的定义与使用
```
$ s="hello" # 注意不能有空格
$ echo $s # 输出该变量
hello
$ s = 3
$ echo $s
3
$ s2=$s # 变量赋值
$ echo $s2
3
$ s3=${s} # 同上
echo $s3
3
$ s4=$(pwd) # ()用作命令替换，pwd表示当前目录
$ echo $s4
/d
```
##### 运算
1. 使用 expr 执行运算
```
$ result=$( expr 5 + 5) # 注意要有空格！否则将识别为字符串
$ echo $result
10
$ result=$(expr 5 \* 5) # * 乘法运算符需要转置
$ echo $result
```
2. 使用 [] 执行运算
```
$ result=$[ 5 + 5 ]  # 
$ echo $result
10
$ result=$[ 5 * 5 ] # []不需要转置
$ echo $result
25
```
##### 控制
使用 if/else/fi 语法进行控制
1. 数值比较
```
$ if [[ 3 > 7 ]]; then 
>echo "yes"
>else
>echo "no"
>fi
no
```
也可以用一下比较符
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200902172951998.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2x6cl9wcw==,size_16,color_FFFFFF,t_70#pic_center)

2. 字符串比较

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200902173617484.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2x6cl9wcw==,size_16,color_FFFFFF,t_70#pic_center)

3. 文件比较
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200902173628596.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2x6cl9wcw==,size_16,color_FFFFFF,t_70#pic_center)
##### 循环
1. for循环
c语言风格
```
$ vi test.sh
键入：
num=0
for (( i = 0; i < 10; i++ )); do # C语言风格
	num=$[$num + $i]
done
echo "result = ${num}"
$ sh test.sh
result = 45
```
python风格
```
file="data" # data为要读取的文件名
IFS_OLD=$IFS  # IFS为文件分隔符，默认为空白，这句命令将IFS存储在IFS_OLD变量中
IFS=$'\n' # 将IFS定为段落分隔符
for line in $(cat $file) # 用cat读取整个文件，并用IFS来进行分割
do
	echo "${line}" # 输出
done
IFS=${IFS_OLD} # 将IFS还原
```
其中，data文件为：
```
The quick brown fox jumps over the lazy dog.
The quick brown fox jumps over the lazy dog.
The quick brown fox jumps over the lazy dog.
The quick brown fox jumps over the lazy dog.
```
输出得到：
```
The quick brown fox jumps over the lazy dog.
The quick brown fox jumps over the lazy dog.
The quick brown fox jumps over the lazy dog.
The quick brown fox jumps over the lazy dog.
```
2. while循环
```
i=1
while [[ i -lt 10 ]]; do
		i=$(expr $i + 1)
done
echo $i
```
3. until循环
 ```
 i=1
until [[ i -eq 10 ]]; do
		i=$(expr $i + 1)
done
echo $i
 ```
##### 容器
 待更新。。。（等用到的时候再写hh）
 
##### 方法
待更新。。。
### 文件
##### 文件读取
文件的读取可以参考for循环那一节，这里就不赘叙了
##### 目录的读取
用ls命令结合for in来读取目录
```
for item in $(ls $(pwd)); do
	itemPath="$(pwd)/${item}"
	if [[ -d $itemPath ]]; then
		echo "目录${itemPath}“
	else
		echo "文件${itemPath}"
	fi
done
```
##### 文件写入 
1. 使用 > 输出重定向符清空文件然后把内容写入文件中
2. 使用 >> 输出重定向符把内容追加到文件中
```
file="data"
file1="data1"
sort ${file} | uniq > ${destfile} # sort对文件按行进行排序 ， | 分隔符把前一项的输出作为后一项的输入， uniq 进行去重操作
```
其中data内容为：
```
The quick brown fox jumps over the lazy dog.
The quick brown fox jumps over the lazy dog.
The quick brown fox jumps over the lazy dog.
The quick brown
fox
zz
```
得到data1的内容为：
```
fox
The quick brown
The quick brown fox jumps over the lazy dog.
zz
```
### sed流编辑
待更新。。。
### 模块
待更新。。。
### 输入和菜单
待更新。。。

学习自 https://my.oschina.net/FEEDFACF/blog/1789695
