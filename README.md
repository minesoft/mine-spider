# mine spider 【吾软爬虫】

目标：

[ ] 强大的全流程模块化设计
[ ] 灵活可扩展，自定义模块实现
[ ] 极简配置，动态增删爬虫
[ ] 数据可视化
[ ] 支持多线程，支持分布式
[ ] 支持动态渲染
[ ] 尽量少的框架依赖

## 强大的全流程模块化设计 【**开发中**】

组件:

链接生成器：包含初始化链接生成器、定时器链接生成器，生成的链接会放入`链接管理器`。

页面下载器：从`链接管理器`获取链接，然后进行请求，获取到内容，转发给`页面提取器`。

页面提取器：将爬取的内容进行提取，将内容放入`内容存储器`，将链接放入`链接管理器`。

内容存储器：将`页面提取器`解析到的内容，通过输出、存储、推送等操作进行持久化处理。

链接管理器：保存所有链接，包括生成、提取的链接。

属性管理器：键值对，用来保存过程中要用到的属性。

### 链接生成器

包含两个方法，一个初始化方法，一个定时器方法，用来生成链接。

### 页面下载器

包含一个方法，传入请求，返回页面内容

### 页面提取器

### 链接管理器

### 内容存储器

### 属性管理器
