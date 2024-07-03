-- done
ALTER TABLE psy_consult_work ADD COLUMN consult_type CHAR(4) NOT NULL DEFAULT '1' COMMENT '1 咨询 2倾听 3督导'  AFTER consult_id;
update psy_consult_work set consult_type = '1' where consult_type is null;

ALTER TABLE psy_consult_order ADD COLUMN consult_type CHAR(4) NOT NULL DEFAULT '1' COMMENT '1 咨询 2倾听 3督导' AFTER nick_name;
ALTER TABLE psy_consult ADD COLUMN team_type CHAR(4) NOT NULL DEFAULT '' COMMENT '团督类型: 1 个督 2团督 '  AFTER user_id;

ALTER TABLE `psy_consultant_team_supervision`
    CHANGE COLUMN `id` `id` BIGINT(19) NOT NULL AUTO_INCREMENT COMMENT '团督主键' FIRST;