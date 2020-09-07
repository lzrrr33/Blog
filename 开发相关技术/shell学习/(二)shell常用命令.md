# 后端开发技能学习(三) shell常用命令
### 查找
##### find
find用于对文件进行查找
格式：find pathname -options [-print -exec -ok ...]
参数：
pathname：目录
options：选项
-print：输出匹配结果
-exec：对匹配的文件进行操作
-ok：同上，不过会给出提示
|option|含义|
| ---|--- |
|-name|按文件名查找|
|-perm|按文件权限查找|
|-prune||
|-user|按照文件属主查找|
|-group|按文件属组查找|
|-mtime|按照文件更改时间查找|
|-nogroup|无组文件查找|
|-nouser|无主文件查找|
|-newer f1 ! f2|比f1新比f2旧的文件|
|-size n|查找文件长度为n|
|depth|在子目录继续查找|
|-follow|遇到符号链接，跟踪到指向的文件|
|-type|指定类型文件|

栗子：
```
find . -name "test.sh" # 查找名称为test.sh的文件
find . -name test.sh | xargs sh # 查找该文件并执行
find /apps -path "/apps/bin" -prune -o -print # 避开bin目录查找
find . -size +100000c -print # 查找大于1M字节的文件（不加c表示块，即512字节）
```
|type|类型|
|---|---|
|b|块设备文件|
|d|目录|
|c|字符设备文件|
|p|管道文件|
|l|符号链接文件|
|f|普通文件|


##### grep
grep用于对文件内容进行查找
通用格式：
grep [option] "string_to_find" filename
选项：
|option|full name|meaning|
|---|---|---|
|-a -|-text|不忽略二进制数据|
|-A<行数>|--after-contxt<行数>|显示查找行后n行的内容|
|-B<行数>|--before-context|显示查找行前n行的内容|
|-c|--count|计算符合条件的行数量|
|-C|--context|显示改行前后的内容|
|-d|--directories| # 可以用于对目录进行查找|
|-e<表达式>|-regexp<表达式>|用正则表达式查找|
|-E |--extended-regexp| 延申正则表达式|
|-f<范本文件>| --file=<规则文件>|用范本文件查找|
|-F| --fixed-regexp|将范本样式视为固定字符串的列表|
|-G| --basic-regexp| 将范本样式视为普通的表示法来使用|
|-h |--no-filename|在显示符合范本样式的那一列之前，不标示该列所属的文件名称| 
|-H |--with-filename|在显示符合范本样式的那一列之前，标示该列的文件名称|
|-i |--ignore-case | 忽略字符大小写的差别|
|-l |--file-with-matches| 列出文件内容符合指定的范本样式的文件名称|
|-L |--files-without-match| 列出文件内容不符合指定的范本样式的文件名称|
|-n |--line-number| 在显示符合范本样式的那一列之前，标示出该列的编号|
|-q |--quiet或--silent|不显示任何信息|
|-R/-r|--recursive|此参数的效果和指定“-d recurse”参数相同|
|-s |--no-messages | 不显示错误信息|
|-v |--revert-match| 反转查找|
|-V| --version| 显示版本信息|
|-w| --word-regexp|只显示全字符合的列|
|-x |--line-regexp | 只显示全列符合的列|
|-y |-| 此参数效果跟“-i”相同|
|-o |-|只输出文件中匹配到的部分|
##### 正则表达式
```
^        # 锚定行的开始 如：'^grep'匹配所有以grep开头的行。 
$        # 锚定行的结束 如：'grep$'匹配所有以grep结尾的行。 
.        # 匹配一个非换行符的字符 如：'gr.p'匹配gr后接一个任意字符，然后是p。 
*         # 匹配零个或多个先前字符 如：'*grep'匹配所有一个或多个空格后紧跟grep的行。 
.*        # 一起用代表任意字符。 [] # 匹配一个指定范围内的字符，如'[Gg]rep'匹配Grep和grep。 
[^]         # 匹配一个不在指定范围内的字符，如：'[^A-FH-Z]rep'匹配不包含A-R和T-Z的一个字母开头，紧跟rep的行。 
\(..\)       # 标记匹配字符，如'\(love\)'，love被标记为1。 
\<        # 锚定单词的开始，如:'\<grep'匹配包含以grep开头的单词的行。 
\>          # 锚定单词的结束，如'grep\>'匹配包含以grep结尾的单词的行。 
x\{m\}       # 重复字符x，m次，如：'0\{5\}'匹配包含5个o的行。 
x\{m,\}       # 重复字符x,至少m次，如：'o\{5,\}'匹配至少有5个o的行。x\{m,n\} # 重复字符x，至少m次，不多于n次，如：'o\{5,10\}'匹配5--10个o的行。 
\w           # 匹配文字和数字字符，也就是[A-Za-z0-9]，如：'G\w*p'匹配以G后跟零个或多个文字或数字字符，然后是p。 
\W              # \w的反置形式，匹配一个或多个非单词字符，如点号句号等。 
\b              # 单词锁定符，如: '\bgrep\b'只匹配grep。
```
##### 常见用法
```
grep "hello" file1 file2 file3 # 在多个文件中查找
grep -v "hello" file # 查找不匹配的行
grep -E "[1-9]+"  file # 用正则表达式查找
egrep "[1-9]+" file # 同上
egrep -o "[a-z]" file # 只输出文中匹配到的部分
grep -n "1" file # 同时输出行数
grep -b -o "1" file  # 同时输出其位置偏移
grep -l "1" file1 file2 file3 # 查找匹配到的文件
grep "1" . -r -n # 递归搜索目录下的所有文件
grep -e "1" -e "2" file # 多个样式匹配
grep -r "hello" . --include *.{txt,sh} # 在指定文件中进行查找
grep -r "hello" . --exclude *.md # 排除指定文件进行查找
grep -r "hello" . --exclude-from filelist # 排除多个文件进行查找
grep -q "test" filename  # 不会输出任何信息，如果命令运行成功返回0，失败则返回非0值。一般用于条件测试。
```
### 压缩
##### zip
先安装压缩软件：
```
yum install -y zip
yum install -y unzip
```

常见用法：
```
zip -r data.zip data # 压缩data文件 ，-r 递归处理
unzip data.zip # 将文件解压到当前目录
unzip data.zip -d mydata # 将文件解压到mydata目录
zip -r file12.zip file1 file2 # 压缩多个文件
unzip file\*.zip # 解压多个文件
unzip -v data.zip # 查看压缩文件的内容
unzip -t data.zip # 验证压缩文件是否完整
unzip -j data.zip # 将压缩文件里的所有文件解压到同一目录
```
##### tar
安装
yum install -y tar
语法：tar [主选项+辅选项] 文件/目录
主选项（必选项，且只能存在一个）
|option|mean|
|---|---|
|-c|建立压缩文件|
|-x|解开一个压缩文件|
|-t|查看压缩文件|

辅选项
|option|mean|
|---|---|
|-z|使用gzip压缩/解压tar.gz|
|-j|使用bzip2压缩/解压tar.bz2|
|-f|目标文件名字后面直接接文件名|
|-v|压缩过程中显示文件|
|-p|使用源文件的原来属性|
|-P|可以使用绝对路径来压缩|
|-N|按时间来进行打包|
|--exclude file|排除file文件打包|
常见用法
```
# 压缩
tar -cvf file.tar file # 仅打包，不压缩，并在打包时显示文件
tar -zcvf file.tar.gz # 以gz格式进行压缩
tar -jcvf # 以bzip2格式进行压缩
# 特别注意，在参数 f 之后的文件档名是自己取的，我们习惯上都用 .tar 来作为辨识。
# 如果加 z 参数，则以 .tar.gz 或 .tgz 来代表 gzip 压缩过的 tar file ～
# 如果加 j 参数，则以 .tar.bz2 来作为附档名
# 查看
tar -ztvf file.tar.gz # 查看用gz格式压缩的文件（bzip2同理）
# 解压缩
tar -zxvf file.tar.gz # 将文件解压到当前目录
tar -zxvf file.tar.gz file/d1 # 只解压d1文件内容
tar -N "2020/09/06" -zcvf file.tar.gz file # 只压缩2020/09/06之后的文件
tar -zxvpf file.tar.gz file # 保留原文件的权限属性
tar --exclude file/d1 -zcvf file.tar.gz file # 排除的d1文件进行压缩
```

##### rar
待更新。。。

### 磁盘
##### mount
mount 命令用于挂载一个文件系统，或是显示已挂载的文件系统的信息。
挂载：就是将设备文件中的顶级目录连接到 Linux 根目录下的某一目录（最好是空目录），访问此目录就等同于访问设备文件。如果不挂载，通过Linux系统中的图形界面系统可以查看找到硬件设备，但命令行方式无法找到。
直接运行mount命令将显示所有挂载的文件系统。
```
$ mount 
C: on /c type ntfs (binary,noacl,posix=0,user,noumount,auto)
D: on /d type ntfs (binary,noacl,posix=0,user,noumount,auto)
```
可以看到这里将C盘和D盘挂载到了/c和/d目录下
通用格式为：
设备名 on 挂载目录 文件类型 相关挂载选项
常见用法：
```
$ mount -t ext3 # 只显示某一文件类型的挂载
$ sudo mount -t iso9660 -o loop im_toby.iso /mnt/dvd # 挂载一个 iso 文件到 /mnt/dvd 目录
$ sudo mount /dev/sda5 /mydata # 挂载一个分区到 /mydata 目录
$ sudo mkdir /mnt/local
$ sudo mount -t nfs hostname:/local /mnt/local # 挂载一个远程 NFS 共享目录到 /mnt/local 下
$ sudo umount /mnt/local # 卸载该文件系统
```
##### df
该命令用于获取硬盘被占用了多少空间
通用格式：
```
df [-ahikHTm] [目录或文件名]
```
参数选项：
|option|mean|
|---|---|
|-a|列出所有的文件系统|
|-k|以k为单位显示|
|-m|以M为单位显示|
|-h|自动单位显示|
|-H|以M=1000k作为进位方式|
|-T|显示文件系统类型|
|-i|以inode的数量来显示|

##### du
 du命令是对文件和目录磁盘使用的空间的查看。
 通用格式：
 ```
 du [-ahskm] 文件或目录名称
 ```
 参数选项
 |option|mean|
 |---|---|
 |-a|列出所有的文件于目录容量，默认仅统计目录底下的文件量|
|-h|同df|
|-s|列出总量，不列出占用量|
|-S|子目录不进行总计|
|-k|同df|
|-m|同df|

##### fdisk
fdisk 是 Linux 的磁盘分区表操作工具
通用格式：
```
fdisk [-l] 装置名称
```
-l ：输出后面接的装置所有的分区内容。若仅有 fdisk -l 时， 则系统将会把整个系统内能够搜寻到的装置的分区均列出来。
##### fsck
fsck（file system check）用来检查和维护不一致的文件系统
用法：
```
fsck [-t 文件系统] [-ACay] 装置名称
```
##### mkfs
mkfs用于磁盘格式化
用法：
```
mkfs [-t 文件系统格式] 装置文件名
```
参数选项：
-t ：可以接文件系统格式，例如 ext3, ext2, vfat 等(系统有支持才会生效) 

### 权限
##### 基本概念
查看文件权限：
```
$ ls -l
drwxr-xr-x 1 user 197609     0  9月  6 12:03 Blog/
-rw-r--r-- 1 user 197609    11  9月  3 23:00 test.sh
# 其中d代表目录，-代表文件
# 第一个xxx（x为rwx）表示所有者权限
# 第二个xxx表示所在组的权限
# 第三个xxx表示其他用户的权限，新创建的文件默认权限为-rw-r--r--,所有者能读写，其他用户只能读
# 后面的1表示文件数目
# user表示创建者
# 后面的数字表示文件大小
# 后面是日期和文件/文件夹名字
```
权限分类：
rwx（读，写，执行）
augo（所有，所有者，所属组，其他）
##### 用户管理
查看用户详情： 
```
$ id root
uid=0(root) gid=0(root) groups=0(root)
$ id lzr
uid=1025(lzr) gid=1025(lzr) groups=1025(lzr) # 显示该用户的uid，gid组id，所属的组
useradd user1 # 新建用户
passwd user1 # 设立密码
userdel -r user # 删除用户
gpasswd -a user1 root # 把用户添加到root组中
gpasswd -d user1 root # 从root组中删除用户
```
##### chmod
chmod命令用于更改文件权限
格式1：
chmod [agou] [+ -] [rwx] filename
其中：
agou表示文件权限更改的范围，分别问all，group，others，user 
+-表示增加或删除权限
格式2：
chmod xxx filename
三类用户一起设置第一个x代表所有者，第二个代表所属组，第三个代表其他
用r(4)w(2)x(1)来表示权限
chmod不能处理每个用户权限不一样时的问题
##### ACL
不建议和chmod混合使用
待更新。。。
##### sudo
sudo命令用来更改应用程序或者命令的执行权限
待更新。。。
### 进程
待更新。。。
### 其他
待更新。。。
