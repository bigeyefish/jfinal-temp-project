#sql("findUserByName")
  select * from user t where t.user_name = #para(0)
#end