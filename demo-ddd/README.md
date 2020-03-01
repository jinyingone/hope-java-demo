ddd学习资料:https://www.infoq.cn/article/domain-driven-design-quickly
类图:

资源库:
rabbit
https://hub.docker.com/_/rabbitmq
sudo docker run -d --hostname my-rabbit --name demo-rabbit-m -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin rabbitmq:3-management
username:guest password guest