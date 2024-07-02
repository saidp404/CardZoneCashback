package az.pashabank.cardzone.scheduler;

import az.pashabank.cardzone.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledCashback {
    private final TransactionService transactionService;

    @Scheduled(cron = "0 0 18 * * ?")
//    @Scheduled(cron = "0 * * ? * *")
    public void applyCashbacks(){
        transactionService.getAndApplyCashback();
    }
}
