#sql("findTaskByStatus")
  select * from task t where t.is_active = #para(0)
#end
#sql("findJobByCode")
  select * from job t where t.code = #para(0)
#end
#sql("findUsersByFamily")
  select id, nick_name, family_id from user t where t.family_id = #para(0)
#end