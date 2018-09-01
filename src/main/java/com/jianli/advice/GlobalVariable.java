package com.jianli.advice;

import com.jianli.commons.StringUtils;
import com.jianli.exception.AuthenticException;


/**
 * @author chendurex
 * @date 2018-09-01 23:33
 */
public class GlobalVariable {
    private final static ThreadLocal<NameValuable> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(String uid, String ticket, String backup) {
        if (StringUtils.isEmpty(uid)) {
            throw new AuthenticException("请传入用户ID和凭证");
        }
        THREAD_LOCAL.set(new NameValuable(Integer.valueOf(uid), ticket, backup));
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

    public static int uid() {
        return get() != null ? get().getUid() : 0 ;
    }

    public static String backup() {
        return get() != null ? get().getBackup() : null ;
    }

    public static String ticket() {
        return get() != null ? get().getTicket() : null ;
    }

    private static NameValuable get() {
        return THREAD_LOCAL.get();
    }

    private static class NameValuable {
        private Integer uid;
        private String ticket;
        private String backup;

        NameValuable(Integer uid, String ticket, String backup) {
            this.uid = uid;
            this.ticket = ticket;
            this.backup = backup;
        }

        Integer getUid() {
            return uid;
        }

        String getTicket() {
            return ticket;
        }

        String getBackup() {
            return backup;
        }
    }



}
