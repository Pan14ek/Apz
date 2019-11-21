package ua.nure.apz.makieiev.apz.dto.gift;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ua.nure.apz.makieiev.apz.model.GiftCategory;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiftDto {

    private String title;
    private String description;
    private GiftCategory giftCategory;

}