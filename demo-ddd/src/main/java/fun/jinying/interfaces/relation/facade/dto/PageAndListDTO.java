package fun.jinying.interfaces.relation.facade.dto;

import fun.jinying.domain.relation.model.Relation;
import fun.jinying.interfaces.relation.facade.internal.RelationDtoAssembler;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 分页数据
 * @author: sjy
 * @create: 2020-03-08 14:42
 **/
@Data
public class PageAndListDTO {
    private List<RelationDTO> list;
    private PageInfo pageInfo;

    public void init(List<Relation> relationList, int totalCount) {
        List<RelationDTO> relationDTOList = relationList.stream().map(RelationDtoAssembler::toDTO).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo();
        pageInfo.setTotalCount(totalCount);
        pageInfo.setMore(!relationList.isEmpty());
        RelationDTO last = CollectionUtils.lastElement(relationDTOList);
        pageInfo.setScore(last != null ? -last.getTime().getTime() : 0);
        this.setPageInfo(pageInfo);
        this.setList(relationDTOList);
    }
}
