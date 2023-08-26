use newsteelsystem;
update supply_gooddetailstype set gooddetailsType = "分条" where gooddetailsType = "分条加工";
update supply_gooddetailstype set gooddetailsType = "分卷" where gooddetailsType = "分卷加工";
update supply_gooddetailstype set gooddetailsType = "剪板" where gooddetailsType = "剪板加工";
update supply_gooddetailstype set gooddetailsType = "折弯" where gooddetailsType = "折弯加工";
update supply_gooddetailstype set gooddetailsType = "激光" where gooddetailsType = "激光加工";
delete from supply_gooddetailstype where gooddetailsType is null;