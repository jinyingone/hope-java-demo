# DDD在社交网络的实战

**一个没有落地的DDD，不是DDD**。如何落地，本文通过一个社交网络的例子展示我们DDD实施的过程。当然，本文中的例子是我们实际例子的微缩版，只在说明意图。

## 什么是DDD

**领域驱动设计**（Domain-Driven-Design）作为一种软件开发方法，它可以帮助我们设计高质量的软件模型。对，这只是一套**方法论**，2003 年，Eric Evans 发布了影响深远的《Domain-Driven Design: Tackling Complexity in the Heart of Software》（领域驱动设计：软件核心复杂性应对之道）一书，DDD 问世。为何现在才被大家重视呢，为什么过了这么久才被大家所熟知呢，因为随着近些年**微服务野蛮生长**，是我们重新认知到了模型设计的重要性，可以使用**DDD指导微服务**设计

### DDD能带来什么

- **建立通用语言** -- 围绕领域模型建立的一种语言，团队所有成员都使用这种语言把团队的所有活动与软件联系起来。
- **设计就是代码，代码就是设计** -- 设计是关于软件如何工作的，最好的编码设计来自于多次的实验，这得益于敏捷的发现过程。
- **突出核心价值** -- 软件的核心价值不是你采用何种技术，而是你能给用户带来什么，模型的设计中心就是核心域。

### DDD核心概念

**领域**（Domain）-- 每个软件程序是为了执行用户的某项活动，或是满足用户的某种需求。这些用户应用软件的问题区域就是软件的领域。

**核心领域**（CORE DOMAIN）-- 模型的独特部分，是用户的核心目标，它使得应用程序与众不同并且有价值。

**限界上下文**（BOUNDED CONTEXT）-- 特定模型的应用限界(边界)。限界上下文使团队所有成员能够明确地知道什么必须保持一致，什么必须独立开发。

**实体**（ENTITY）-- 一种有唯一标识的可变化的对象，它不是由属性来定义的，而是通过一连串的连续事件和标识定义的。

**值对象**（VALUE OBJECT）-- 一种描述了某种特征或属性但没有概念标识的不可变对象。

**服务**（SERVICE）-- 一些操作从概念上讲不属于任何对象，可以独立出来作为服务。

**存储库**（REPOSITORY）-- 一种把存储、检索和搜索行为封装起来的机制，它类似于一个对象集合。

**聚合**（AGGREGATE）-- 聚合就是一组相关对象的集合，我们把聚合作为数据修改的单元。外部对象只能引用聚合中的一个成员，我们把它称为聚合根。在聚合的边界之内应用一组一致的规则。

**模块**（MODULE）-- 一个命名的容器，用于存放领域内聚在一起的类。将类放在不同的模块中的目的在于达到松耦合的目的。

**工厂**（FACTORY）-- 一种封装机制，把复杂的创建逻辑封装起来，并为客户抽象出所创建的对象的类型。

### DDD分层架构

　　在分层架构中，我们将一个复杂的系统分为不通的层，每个层都具有良好内聚性，并切只依赖比自身更低的层。但是通常我们降基础设施层设计层设计成环绕模式，它可以被任意层依赖，也可以实现任意层定义的接口。

![DDD分层](https://i.loli.net/2020/04/13/3HcIJtFilWxBezw.png)

用户接口层（interfaces）-- 处理显示和用户请求，不包括业务逻辑，可以包含一些基本的参数检查。

应用层（application）--  处理持久化事务、发送消息、安全认证等，是很薄的一层，主要协调领域对象的操作。

领域层（domain）-- 处理核心业务逻辑，不包括技术实现细节。领域层是业务软件的核心。

基础设施层（infrastructure）-- 处理纯技术细节，为其他层提供技术支持，也可用于封装调用的外部系统细节。例如：持久化的实现，消息中间件的实现，工具类等

### DDD包结构

```config
interfaces
    ...dto
        ...XDTO.java
    ...XController.java
application
    ...XAppService.java
domain
    ...model
        ...XEntity.java
        ...XValueObject.java
        ...XCreatedEvent.java
    ...repository
        ...XRepository.java
    ...service
        ...XService.java
infrastrctre
    ...presistence
        ...MyXRepository.java
    ...message
        ...XMessageConsumer.java
        ...XMessageProducer.java
    ...service
        ...XService.java
    ...utils
XApplication.java
XApplicationConfig.java
```

## DDD实战

　　参考一个简答的社交网络概念，落地DDD。

### 社交领域核心概念

 **用户**（User）-- 一个**账户**，并以用户名识别。任何个人，企业，机器人等，都可成为系统用户。我们实际上希望每个用户都是一个个人，但实际并不总是这样。

**关系**（Relation）-- 人和人或人和事物之间的某种性质的联系。用户之间可以通过**关注动作**产生关注和粉丝,互相关注等关系。通过关系，将所有的用户都联系在一起，就像一张网一样。按照**六度空间理论**,你和任何一个陌生人之间所间隔的人不会超过六个。  

**动态**（Feed）-- 用户发布文字，图片，视频，评论等用户生产的内容，也可以是系统推荐给用户的引导内容，泛指一切用户看到的内容。  

**时间线**（Timeline）-- 我关注的人发布的Feed，推荐给用户的内容等按照时间顺序由近到远的有序列表。  

**主页**（Profile）-- 我自己的个人主页，包括我的介绍，发布的动态，粉丝数等。

### 领域设计

  系统中包括两个核心的业务逻辑，可以分为两个业务上下文，用户（User）和动态（Feed），分别表示为两个类User和Feed。参考如下类图：
  
![社交网络DDD类图-User](https://i.loli.net/2020/04/14/uUBFDV6q8iyZERa.png)

用户（User）-- 具有唯一标识且具有连续性，因此是一个实体，同时，User也是用户上下文的聚合根。

用户关系（UserRelation）-- 没有唯一标识，且根据属性就能区分两个关系是否一样，因此是一个值对象。隶属于User聚合，且引用User中的属性userId作为和User的关联。

![社交网络DDD类图-Feed](https://i.loli.net/2020/04/14/HnGwuIvYT29qEr1.png)

动态（Feed）-- 具有唯一标识feedId，且是变化的，因此是一个实体。同时也是动态上下文的聚合根。

时间线（timeline） -- 主要由feedId列表组成，是一个值对象，属于Feed上线文。

个人动态（homeline） -- 主要由feedId列表组成，是一个值对象，属于Feed上下文。

### Coding

　　参考user聚合，实现DDD，完整示例请参考：<https://github.com/jinyingone/hope-java-demo/tree/master/demo-ddd>，按照层次结构依次需要实现

1. UserController（用户接口层）
2. UserAppService（应用层）
3. User + UserRepository（领域层）
4. MyUserRepository（基础设施层）

#### 用户接口层 UserController

```java
package fun.jinying.interfaces.user;

import fun.jinying.interfaces.user.facade.UserServiceFacade;
import fun.jinying.interfaces.user.facade.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户相关接口
 * @author: sjy
 * @create: 2020-02-26 22:32
 **/
@RestController
@RequestMapping("/v1/demo/ddd/user")
public class UserController {
    @Autowired
    private UserServiceFacade userServiceFacade;

    @RequestMapping("/register")
    public UserDTO register(@Validated UserRegisterCmd cmd) {
        return userServiceFacade.register(cmd.getPhone(), cmd.getSmsCode());
    }

    @RequestMapping("/login")
    public UserDTO login(@Validated UserLoginCmd cmd) {
        return userServiceFacade.login(cmd.getPhone(), cmd.getSmsCode());
    }

    @RequestMapping("/update")
    public UserDTO update(@Validated UserUpdateCmd cmd) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(cmd.getUserId());
        userDTO.setAvatar(cmd.getAvatar());
        userDTO.setUserName(cmd.getUserName());
        userDTO.setPhone(cmd.getPhone());
        return userServiceFacade.update(userDTO);
    }
}
```

#### 应用层UserAppService

```java
package fun.jinying.application.impl;

import fun.jinying.application.UserAppService;
import fun.jinying.domain.shard.model.EventProducer;
import fun.jinying.domain.user.model.User;
import fun.jinying.domain.user.model.UserEvent;
import fun.jinying.domain.user.model.UserService;
import fun.jinying.domain.user.model.UserUpdater;
import fun.jinying.domain.user.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @description:
 * @author: sjy
 * @create: 2020-02-28 18:08
 **/
@Component
public class UserAppServiceImpl implements UserAppService {
    private UserRepository userRepository;
    private EventProducer<UserEvent> userEventProducer;
    private UserService userService;

    public UserAppServiceImpl(UserRepository userRepository, EventProducer<UserEvent> userEventProducer, UserService userService) {
        this.userRepository = userRepository;
        this.userEventProducer = userEventProducer;
        this.userService = userService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User register(String phone) {
        Integer userId = userRepository.nextUserId();
        String userName = userService.getRandomUserName();
        String avatar = userService.getRandomAvatar();
        User user = User.createNewUser(userId, phone, userName, avatar);
        userRepository.saveUser(user);
        UserEvent userEvent = user.register();
        userEventProducer.sendEvent(userEvent);
        return user;
    }

    @Override
    public Optional<User> getRegisteredUser(String phone) {
        return userRepository.getByPhone(phone);
    }

    @Override
    public void login(User user) {
        userEventProducer.sendEvent(user.login());
    }

    @Override
    public Optional<User> getUser(String userId) {
        return userRepository.getByUserId(userId);
    }

    @Override
    public User update(String userId, UserUpdater userUpdater) {
        User user = userRepository.getByUserId(userId).orElseThrow(() -> new IllegalStateException(userId + "not exits"));
        userRepository.update(user.getUserId(), userUpdater);
        userEventProducer.sendEvent(user.updateUserName(userUpdater.getUserName()));
        return user;
    }

}

```

#### 领域层User

```java
package fun.jinying.domain.user.model;

import fun.jinying.domain.shard.model.Entity;
import lombok.*;

import java.util.Collections;
import java.util.Date;

/**
 * @description: 用户
 * @author: sjy
 * @create: 2020-02-26 22:01
 **/
@Getter
@Setter(AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode(of = {"userId"})
public class User implements Entity {
    private Integer userId;
    private String userName;
    private String phone;
    private String avatar;
    private Date createTime;
    private Date updateTime;

    /**
     * 创建一个新用户，这是一个工厂方法
     * @param userId userid
     * @param phone 手机号
     * @param userName 名称
     * @param avatar 头像
     * @return User
     */
    public static User createNewUser(Integer userId,String phone,String userName,String avatar) {
        User user = new User();
        user.setUserId(userId);
        user.setPhone(phone);
        user.setAvatar(avatar);
        user.setUserName(userName);
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        return user;
    }

    /**
     * 注册
     * @return 注册事件
     */
    public UserEvent register(){
        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent();
        userRegisteredEvent.setUserId(this.userId.toString());
        userRegisteredEvent.setType(UserEventTypeEnum.REGISGERED);
        return userRegisteredEvent;
    }

    /**
     * 登陆
     * @return 登陆事件
     */
    public UserEvent login() {
        UserLoggedEvent event = new UserLoggedEvent();
        event.setUserId(this.getUserId().toString());
        event.setType(UserEventTypeEnum.LOGED);
        return event;
    }

    /**
     * 更新用户名
     * @param userName 新的用户名
     * @return 更新事件
     */
    public UserUpdatedEvent updateUserName(String userName){
        this.userName = userName;

        UserUpdatedEvent userUpdatedEvent = new UserUpdatedEvent();
        userUpdatedEvent.setType(UserEventTypeEnum.UPDATED);
        userUpdatedEvent.setUserId(this.getUserId().toString());
        userUpdatedEvent.setUpdatedFields(Collections.singletonMap("userName",userName));
        return userUpdatedEvent ;
    }

}
```

#### 领域层UserRepository

```java
/**
 * @description: 用户仓储
 * @author: sjy
 * @create: 2020-02-26 22:30
 **/
public interface UserRepository {
    /**
     * 下一个userId
     *
     * @return
     */
    Integer nextUserId();

    /**
     * 默认昵称序列
     *
     * @return
     */
    Integer nextUserNameSequence();

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    int saveUser(User user);

    /**
     * 根据手机号查找
     *
     * @param phone
     * @return
     */
    Optional<User> getByPhone(String phone);

    /**
     * 根据id查找
     *
     * @param userId
     * @return
     */
    Optional<User> getByUserId(String userId);

    /**
     * 更新用户
     *
     * @param userId
     * @param userUpdater
     * @return
     */
    int update(Integer userId, UserUpdater userUpdater);
}
```

#### 基础设施层MyUserRepository

```java
package fun.jinying.infrastructure.persistence;

import fun.jinying.domain.user.model.User;
import fun.jinying.domain.user.model.UserUpdater;
import fun.jinying.domain.user.repository.UserRepository;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: sjy
 * @create: 2020-02-28 18:35
 **/
@Component
public class MyUserRepository implements UserRepository {
    @Autowired
    private UserMapper userMapper;
    private static final AtomicInteger userIdSequence = new AtomicInteger(10000);
    private static final AtomicInteger userNameSequence = new AtomicInteger(10000);

    @Override
    public Integer nextUserId() {
        return userIdSequence.incrementAndGet();
    }

    @Override
    public Integer nextUserNameSequence() {
        return userNameSequence.incrementAndGet();
    }

    @Override
    public int saveUser(User user) {
        return userMapper.saveUser(user);
    }

    @Override
    public Optional<User> getByPhone(String phone) {
        return Optional.ofNullable(userMapper.getByPhone(phone));
    }

    @Override
    public Optional<User> getByUserId(String userId) {
        return Optional.ofNullable(userMapper.getByUserId(userId));
    }

    @Override
    public int update(Integer userId, UserUpdater userUpdater) {
        return userMapper.updateUser(userId, userUpdater);
    }

    @Mapper
    @Component
    public interface UserMapper {
        /**
         * 保存用户
         *
         * @param user
         * @return
         */
        @Insert("insert into user(user_id,phone,user_name,avatar,password,create_time,update_time)" +
                "values(#{user.userId},#{user.phone},#{user.userName},#{user.avatar},#{user.password},#{user.createTime},#{user.updateTime})")
        int saveUser(@Param("user") User user);

        /**
         * 根据手机号查找
         *
         * @param phone 手机号
         * @return
         */
        @Select("select user_id,user_name,avatar,phone,create_time,update_time from user where phone=#{phone}")
        User getByPhone(@Param("phone") String phone);

        /**
         * 根据userId查找
         *
         * @param userId
         * @return
         */
        @Select("select user_id,user_name,avatar,phone,create_time,update_time from user where user_id=#{userId}")
        User getByUserId(@Param("userId") String userId);


        int updateUser(@Param("userId") Integer userId, @Param("userUpdater") UserUpdater userUpdater);
    }

}

```

## 总结

　　DDD可以为我们提供一个很好的描述复杂系统的方法论，但是，软件开发没有银弹，DDD并不能解决我们遇到的所有架构上的问题。

## 参考文献

领域驱动设计：软件核心复杂性应对之道

实现领域驱动设计
