# shensuanzi



* Redisson与SpringBoot的版本有些许的依赖关系，如果版本不匹配可能导致Bean注入失败






参考：


@Valid
@NotNull(message = "")
@Length(max = 20, message = "")
@EnumValid(target = StatusEnum.class,message = "auditStatus值异常")
    private Integer auditStatus;


StatusEnum{
success(0,"通过"),
fail(1,"不通过"),
    ;

}
 