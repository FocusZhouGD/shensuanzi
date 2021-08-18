# Transactional 
* 事务生效规则
   1. 私有方法不生效，public方法才生效，并且一定是spring注入的bean进行调用    
   2. 自调用比如this.XX方法(),this.XX方法()中有调用有事务的方法，也是不生效的
   3. 通过self可以生效 self是注入自己 spring通过CGLB动态代理此类
   4. 正常的三层注入,就是spring注入的bean调用
   
* 事务回滚失败
   1. 只有可以传播出异常的并且带有@Transactional注解的方法事务才可以回滚
       ```  
      //可以传播出异常
          @Transactional
          public void createUserPublic(UserEntity entity) {
              userRepository.save(entity);
              if (entity.getName().contains("test"))
                  throw new RuntimeException("invalid username!");
          }
      ```
      ```
          //不出异常
              @Transactional
              public int createUserWrong3(String name) {
                  try {
                      this.createUserPublic(new UserEntity(name));
                  } catch (Exception ex) {
                      log.error("create user failed because {}", ex.getMessage());
                  }
                  return userRepository.findByName(name).size();
              }
      ```
   2. 默认的情况下，出现RuntimeException(非受检异常)或Error的时候，spring才会回滚事务，IO异常和SQL异常都是受检异常
   
      1. 如果希望自己捕获异常进行处理的，可以手动设置让当前事务处于回滚状态
      
      ```    
        @Transactional
        public void createUserRight1(String name) {
            try {
                userRepository.save(new UserEntity(name));
                //注意这里的异常
                throw new RuntimeException("error");
            } catch (Exception ex) {
                log.error("create user failed", ex);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
      ```
    3. 在注解中声明，期望所有的异常都回滚事务（来突破默认不回滚受检异常的限制）
       ```
        @Transactional(rollbackFor = Exception.class)
        public void createUserRight2(String name) throws IOException {
            userRepository.save(new UserEntity(name));
            otherTask();
         }
       ```
* 事务传播性
   1. 场景一： 一个主方法，一个子方法，子方法出异常，不影响主方法 [两个Service，后一个注入前一个，如果通过一个类a方法调用b方法，是不会走spring代理的]
     ```
      正确的做法：
  
       @Transactional
          public void createUserRight(UserEntity entity) {
              createMainUser(entity);
              try {
                  subUserService.createSubUserWithExceptionRight(entity);
              } catch (Exception ex) {
                  // 捕获异常，防止主方法回滚
                  log.error("create sub user error:{}", ex.getMessage());
              }
          }
       子方法：
          @Transactional(propagation = Propagation.REQUIRES_NEW)
              public void createSubUserWithExceptionRight(UserEntity entity) {
                  log.info("createSubUserWithExceptionRight start");
                  userRepository.save(entity);
                  throw new RuntimeException("invalid status");
              }
      
     ```
   
       
       
    
      
      
     