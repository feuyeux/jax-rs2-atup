package org.feuyeux.jaxrs2.atup.cases.service;

import org.feuyeux.jaxrs2.atup.core.info.AtupTestJobInfo;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: Eric Han
 * Date: 1/17/14
 * Time: 12:22 AM
 */
public class TUJobQueue {
    final PriorityQueue<AtupTestJobInfo> jobQueue = new PriorityQueue<>();
    final Random random = new Random(System.nanoTime());
    static final AtomicInteger JOB_ID = new AtomicInteger();

    @Test
    public void testJobQueue() {
        for (int i = 0; i < 10; i++) {
            Integer userId = 1;
            String deviceIp = "10.11.10." + i;
            Integer caseId = i;
            Integer priority = random.nextInt(5);
            AtupTestJobInfo jobInfo = new AtupTestJobInfo(userId, deviceIp, caseId, priority, System.nanoTime());
            jobInfo.setJobId(JOB_ID.getAndIncrement());
            jobQueue.offer(jobInfo);
        }

        AtupTestJobInfo[] jobs = jobQueue.toArray(new AtupTestJobInfo[jobQueue.size()]);
        if (jobs.length > 0) {
            Arrays.sort(jobs);
        }

        System.out.println("********");
        for (AtupTestJobInfo job : jobs) {
            System.out.println(job.toString());
        }

        System.out.println("********");
        while (jobQueue.size() > 0) {
            System.out.println(jobQueue.poll());
        }
    }
}