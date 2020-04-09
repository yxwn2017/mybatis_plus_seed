package com.start.mb.boot.middleware.security;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.start.mb.boot.common.utils.DateUtils;
import com.start.mb.boot.model.entity.PersistentLogins;
import com.start.mb.boot.server.PersistentLoginsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wll
 * @desc MybatisTokenRepositoryImpl
 * @link
 * @date 2020/4/9 4:12 下午
 */
@Slf4j
@Component
@AllArgsConstructor
public class MybatisTokenRepositoryImpl implements PersistentTokenRepository {


    private final  PersistentLoginsService persistentLoginsService;
//    public static final String CREATE_TABLE_SQL = "create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null)";
//    public static final String DEF_TOKEN_BY_SERIES_SQL = "select username,series,token,last_used from persistent_logins where series = ?";
//    public static final String DEF_INSERT_TOKEN_SQL = "insert into persistent_logins (username, series, token, last_used) values(?,?,?,?)";
//    public static final String DEF_UPDATE_TOKEN_SQL = "update persistent_logins set token = ?, last_used = ? where series = ?";
//    public static final String DEF_REMOVE_USER_TOKENS_SQL = "delete from persistent_logins where username = ?";
//    private String tokensBySeriesSql = "select username,series,token,last_used from persistent_logins where series = ?";
//    private String insertTokenSql = "insert into persistent_logins (username, series, token, last_used) values(?,?,?,?)";
//    private String updateTokenSql = "update persistent_logins set token = ?, last_used = ? where series = ?";
//    private String removeUserTokensSql = "delete from persistent_logins where username = ?";
//    private boolean createTableOnStartup;


    protected void initDao() {

    }

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
//        this.getJdbcTemplate().update(this.insertTokenSql, new Object[]{token.getUsername(), token.getSeries(), token.getTokenValue(), token.getDate()});

        PersistentLogins pl =new PersistentLogins();
        pl.setUsername(token.getUsername()).setSeries(token.getSeries()).setToken(token.getTokenValue()).setLastUsed(DateUtils.asLocalDateTime(token.getDate()));
        persistentLoginsService.save(pl);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
//        this.getJdbcTemplate().update(this.updateTokenSql, new Object[]{tokenValue, lastUsed, series});
//        PersistentLogins pl =new PersistentLogins();
//        pl.setToken(tokenValue).setLastUsed(DateUtils.asLocalDateTime(lastUsed));
//        UpdateWrapper<PersistentLogins> updateWrapper = new UpdateWrapper<PersistentLogins>();
//        LambdaUpdateWrapper<PersistentLogins> eq = updateWrapper.lambda().eq(PersistentLogins::getSeries, series);
//
//        persistentLoginsService.update(pl,eq);

        LambdaUpdateWrapper<PersistentLogins> set = Wrappers.<PersistentLogins>lambdaUpdate()
                .eq(PersistentLogins::getSeries, series)
                .set(PersistentLogins::getToken, tokenValue)
                .set(PersistentLogins::getLastUsed, DateUtils.asLocalDateTime(lastUsed));

        boolean update = persistentLoginsService.update(set);
        System.out.println(update );

    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        try {
            PersistentLogins one = persistentLoginsService.getOne(Wrappers.<PersistentLogins>lambdaQuery().eq(PersistentLogins::getSeries, seriesId));


//            return (PersistentRememberMeToken)this.getJdbcTemplate().queryForObject(this.tokensBySeriesSql, (rs, rowNum) -> {
//                return new PersistentRememberMeToken(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4));
//            }, new Object[]{seriesId});
            return new PersistentRememberMeToken(one.getUsername(),one.getSeries(),one.getToken(),DateUtils.asDate(one.getLastUsed()));
        } catch (EmptyResultDataAccessException var3) {
                log.debug("Querying token for series '" + seriesId + "' returned no results.", var3);
        } catch (IncorrectResultSizeDataAccessException var4) {
            log.error("Querying token for series '" + seriesId + "' returned more than one value. Series should be unique");
        } catch (DataAccessException var5) {
            log.error("Failed to load token for series " + seriesId, var5);
        }

        return null;
    }

    @Override
    public void removeUserTokens(String username) {
        persistentLoginsService.remove(Wrappers.<PersistentLogins>lambdaQuery().eq(PersistentLogins::getUsername,username));
//        this.getJdbcTemplate().update(this.removeUserTokensSql, new Object[]{username});
    }

//    public void setCreateTableOnStartup(boolean createTableOnStartup) {
//        this.createTableOnStartup = createTableOnStartup;
//    }
}
