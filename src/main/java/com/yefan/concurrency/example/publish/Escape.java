package com.yefan.concurrency.example.publish;

import com.yefan.concurrency.annoations.NoRecommend;
import com.yefan.concurrency.annoations.NoThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 对象溢出
 * 对象还没创建完成就被其他线程访问到
 */
@Slf4j
@NoThreadSafe
@NoRecommend
public class Escape {

    private int thisCanBeEscape = 1;

    Escape() {
        new InnerClass();
        log.info("init done");
    }

    private class InnerClass{
        public InnerClass() {
            log.info("{}", Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
