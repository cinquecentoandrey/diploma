package com.sems.filestorageservice.scheduling;

import com.sems.filestorageservice.service.FsFileInternalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OutdatedFilesScheduler {

    private final FsFileInternalService fsFileInternalService;

    @Scheduled(cron = "${app.scheduling.cron.outdated-files}")
    @SchedulerLock(name = "OutdatedFilesScheduler",
            lockAtLeastFor = "PT5M", lockAtMostFor = "PT10M")
    public void processOutdatedFiles() {
        log.debug("Running processOutdatedFiles");
        fsFileInternalService.processOutDatedFiles();
    }

}
