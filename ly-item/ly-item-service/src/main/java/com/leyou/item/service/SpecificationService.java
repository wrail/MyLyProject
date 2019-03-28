package com.leyou.item.service;


import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.SpecGroup;
import com.leyou.item.mapper.SpecGroupMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SpecificationService {

    @Autowired
    private SpecGroupMapper mapper;

    public List<SpecGroup> quaryGroupByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> groups = mapper.select(specGroup);
        if (CollectionUtils.isEmpty(groups)){
            throw new LyException(ExceptionEnum.GROUP_NOT_FOUND);
        }
        return groups;
    }
}
