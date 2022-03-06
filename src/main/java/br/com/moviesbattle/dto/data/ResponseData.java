package br.com.moviesbattle.dto.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ResponseData {
    private boolean match;

    public static ResponseData build(final boolean match) {
        return new ResponseData(match);
    }

}
