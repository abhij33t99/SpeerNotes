package com.abhijeet.noteapp.Util;

import com.abhijeet.noteapp.Entity.Users;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JwtUtil.class})
@ExtendWith(SpringExtension.class)
class JwtUtilDiffblueTest {
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Method under test: {@link JwtUtil#generateToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken() {
        // TODO: Complete this test.
        //   Reason: R031 Method may be time-sensitive.
        //   Diffblue Cover was only able to write tests that are time-sensitive.
        //   The assertions don't pass when run at an alternate date, time, and
        //   timezone. Try refactoring the method to take a java.time.Clock instance so
        //   that the time can be parameterized during testing.
        //   See Working with code R031 (https://diff.blue/R031) for details.

        // Arrange and Act
        jwtUtil.generateToken("janedoe");
    }

    /**
     * Method under test: {@link JwtUtil#getClaimsFromToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetClaimsFromToken() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   io.jsonwebtoken.MalformedJwtException: Invalid compact JWT string: Compact JWSs must contain exactly 2 period characters, and compact JWEs must contain exactly 4.  Found: 0
        //       at io.jsonwebtoken.impl.JwtTokenizer.tokenize(JwtTokenizer.java:102)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:370)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:362)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:94)
        //       at io.jsonwebtoken.impl.io.AbstractParser.parse(AbstractParser.java:36)
        //       at io.jsonwebtoken.impl.io.AbstractParser.parse(AbstractParser.java:29)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parseSignedClaims(DefaultJwtParser.java:821)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parseClaimsJws(DefaultJwtParser.java:797)
        //       at com.abhijeet.noteapp.Util.JwtUtil.getClaimsFromToken(JwtUtil.java:29)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        jwtUtil.getClaimsFromToken("ABC123");
    }

    /**
     * Method under test: {@link JwtUtil#validateToken(String, UserDetails)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testValidateToken() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   io.jsonwebtoken.MalformedJwtException: Invalid compact JWT string: Compact JWSs must contain exactly 2 period characters, and compact JWEs must contain exactly 4.  Found: 0
        //       at io.jsonwebtoken.impl.JwtTokenizer.tokenize(JwtTokenizer.java:102)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:370)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:362)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:94)
        //       at io.jsonwebtoken.impl.io.AbstractParser.parse(AbstractParser.java:36)
        //       at io.jsonwebtoken.impl.io.AbstractParser.parse(AbstractParser.java:29)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parseSignedClaims(DefaultJwtParser.java:821)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parseClaimsJws(DefaultJwtParser.java:797)
        //       at com.abhijeet.noteapp.Util.JwtUtil.getClaimsFromToken(JwtUtil.java:29)
        //       at com.abhijeet.noteapp.Util.JwtUtil.validateToken(JwtUtil.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        jwtUtil.validateToken("ABC123", new Users());
    }
}
