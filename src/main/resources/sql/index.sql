#sql("findUserByTel")
  select * from user t where t.tel = #para(0)
#end