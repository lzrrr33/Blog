# 后端开发技能学习(一) Git命令汇总
### 创建版本库
```
$ mkdir learngit  # 创建仓库目录
$ cd learngit
$ pwd  # 显示当前目录
/Users/michael/learngit
$ git init  # 仓库初始化
Initialized empty Git repository in /Users/michael/learngit/.git/

$ git add readme.txt   # 添加文件到仓库
$ git commit -m "wrote a readme file"   # 提交
[master (root-commit) eaadf4e] wrote a readme file
 1 file changed, 2 insertions(+)
 create mode 100644 readme.txt
```
### 版本穿梭
##### 版本回退
```
# 版本回退
$ git status # 查看仓库状态
$ git diff readme.txt  # 查看文件的修改
$ git log --pretty=oneline # 查看所有历史版本  （--pretty=oneline显示简略信息）

$ git reset --hard HEAD^  # 回退到上一个版本  (HEAD~n 回退到上n个版本)
HEAD is now at e475afc add distributed
$ git reset --hard 1094a # 回退到指定版本号的版本 
$ git reflog # 记录每一次命令
```
##### 工作区与版本库
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200831151252196.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2x6cl9wcw==,size_16,color_FFFFFF,t_70#pic_center)==工作区==：自己电脑上的文件夹
==版本库==：目录下的.git文件（stage:暂存区，master:第一个分支，HEAD:指向当前分支）
用git add把文件添加进去，实际上就是把文件修改添加到暂存区；
用git commit提交更改，实际上就是把暂存区的所有内容提交到当前分支。
因为我们创建Git版本库时，Git自动为我们创建了唯一一个master分支，所以，现在，git commit就是往master分支上提交更改。
##### 修改与删除
==Git管理的是修改，而不是文件==
```
$ git checkout -- readme.txt # 撤销工作区的修改
```
命令git checkout -- readme.txt意思就是，把readme.txt文件在工作区的修改全部撤销，这里有两种情况：
一种是readme.txt自修改后还没有被放到暂存区，现在，撤销修改就回到和版本库一模一样的状态；
一种是readme.txt已经添加到暂存区后，又作了修改，现在，撤销修改就回到添加到暂存区后的状态。
总之，就是让这个文件回到最近一次git commit或git add时的状态。
```
$ git reset HEAD <file>  # 把暂存区的修改撤销掉（unstage），重新放回工作区
```
删除文件：先删除工作区，然后git add或 git rm删除版本库文件 
### 远程仓库
##### 设置仓库
1.创建SSH Key。在用户主目录下，看看有没有.ssh目录，如果有，再看看这个目录下有没有id_rsa和id_rsa.pub这两个文件，如果已经有了，可直接跳到下一步。如果没有，打开Shell（Windows下打开Git Bash），创建SSH Key：
```
$ ssh-keygen -t rsa -C "youremail@example.com"
```
id_rsa是私钥，不能泄露出去，id_rsa.pub是公钥，可以放心地告诉任何人。
2.登陆GitHub，打开“Account settings”，“SSH Keys”页面：
然后，点“Add SSH Key”，填上任意Title，在Key文本框里粘贴id_rsa.pub文件的内容：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200831164729488.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2x6cl9wcw==,size_16,color_FFFFFF,t_70#pic_center)
##### 添加远程库
```
echo "# Blog" >> README.md  # 创建仓库文件
$ git init  # 初始化仓库
$ git add README.md 
$ git commit -m "first commit" # （前面做了可省略）
$ git branch -M master  # 更改分支名字
$ git remote add origin git@github.com:lzrrr33/Blog.git  # 关联Github仓库
$ git push -u origin master # 推送当前分支到远程（第一次需要加-u参数，把两个仓库关联起来）
$ git clone git@github.com:michaelliao/gitskills.git  # 克隆仓库到本地
```
可以看到：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200831181007653.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2x6cl9wcw==,size_16,color_FFFFFF,t_70#pic_center)

### 分支管理
##### 创建与合并分支
```
$ git checkout -b dev（$ git switch -c dev） # 创建并切换分支（与以下两条命令等价）
$ git branch dev # 创建分支
$ git checkout dev（$ git switch master） # 切换分支
$ git branch # 查看分支（当前分支会有*号）
$ git merge dev # 将dev分支合并到当前分支
$ git branch -d dev  # 删除分支
$ git log --graph # 查看分支合并图
$ git rebase # 将分叉的提交变成一条直线
待更新。。。
```
### 标签管理
```
$ git tag v1.0  # 对当前分支打标签
$ git tag # 查看所有标签
$ git tag v0.9 f52c633 # 根据commit号打标签
$ git show v0.9 # 查看标签信息
$ git tag -d v0.1 # 删除标签
$ git push origin v1.0 # 推送标签到远程
$ git push origin --tags # 推送所有标签到远程
$ git tag -d v0.9 # 删除本地标签
$ git push origin :refs/tags/v0.9 # 删除远程标签（需先删除本地标签）
```
### 自定义Git
待更新。。。
