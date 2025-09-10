package org.lck.gx.service.impl;

import jakarta.annotation.PostConstruct;
import org.lck.gx.repo.LotteryResultRepository;
import org.lck.gx.service.LotteryResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthLog;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class LotteryResultServiceImpl implements LotteryResultService {


    @Value("${eth.rpcUrl}")
    private String rpcUrl;

    @Value("${contract.lotteryPool}")
    private String contractAddress;

    @Autowired
    private Web3j web3j;


    @Autowired
    private LotteryResultRepository lotteryResultRepository;




    // 保存已处理过的 log hash，防止重复入库
    private final Set<String> processedTx = new HashSet<>();

    // 假设这是你存储已处理区块号的地方，在生产环境中应该使用数据库
    private BigInteger lastProcessedBlock = BigInteger.ZERO;


    public void startPolling() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::pollEvents, 0, 30, TimeUnit.SECONDS); // 每 30s 查询一次
    }


    @PostConstruct
    public void pollEvents() {
        try {
            Event lotteryDrawn = new Event("LotteryDrawn",
                    List.of(
                            new TypeReference<Uint256>() {
                            }, // round
                            new TypeReference<Uint256>() {
                            }, // prizePool
                            new TypeReference<DynamicArray<Uint256>>() {
                            } // winners
                    )
            );

            String encodedEvent = EventEncoder.encode(lotteryDrawn);

            EthLog logs = web3j.ethGetLogs(new org.web3j.protocol.core.methods.request.EthFilter(
                    DefaultBlockParameterName.EARLIEST, // 你可以换成上次查询的 blockNumber
                    DefaultBlockParameterName.LATEST,
                    contractAddress
            ).addSingleTopic(encodedEvent)).send();

            for (EthLog.LogResult<?> logResult : logs.getLogs()) {
                EthLog.LogObject log = (EthLog.LogObject) logResult;

                String txHash = log.getTransactionHash();
                if (processedTx.contains(txHash)) continue; // 已处理过

                List<Type> decoded = FunctionReturnDecoder.decode(
                        log.getData(), lotteryDrawn.getParameters()
                );

                BigInteger round = (BigInteger) decoded.get(0).getValue();
                BigInteger prizePool = (BigInteger) decoded.get(1).getValue();
                List<BigInteger> winners = ((DynamicArray<Uint256>) decoded.get(2)).getValue()
                        .stream().map(Uint256::getValue).toList();

                /*LotteryResult result = new LotteryResult(
                        round.longValue(),
                        prizePool.toString(),
                        winners.stream().map(BigInteger::toString).toList()
                );*/

                //storage.saveResult(result);
                processedTx.add(txHash);

                System.out.println("✅ polled LotteryDrawn round=" + round);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 定时查询并处理 LotteryDrawn 事件。
     * 生产环境中，可以调整 cron 表达式以适应需求。
     */
    @Scheduled(fixedRate = 60000) // 每隔60秒执行一次
    public void queryLotteryDrawnEvents() {
       /* System.out.println("开始查询新区块的 LotteryDrawn 事件...");
        try {
            // 获取当前最新区块号
            BigInteger latestBlock = web3j.ethBlockNumber().send().getBlockNumber();

            // 设置查询的起始和结束区块
            DefaultBlockParameter fromBlock = DefaultBlockParameter.valueOf(lastProcessedBlock.add(BigInteger.ONE));
            DefaultBlockParameter toBlock = DefaultBlockParameter.valueOf(latestBlock);

            // 创建事件过滤器
            LotteryPool contract = LotteryPool.load(
                    "0xB7f8BC63BbcaD18155201308C8f3540b07f84F5e",
                    web3j,
                    null, // 仅查询，无需 credentials
                    null  // 无需 gas provider
            );

            EthFilter filter = contract.lotteryDrawnEventFilter(fromBlock, toBlock);

            // 执行查询
            EthLog ethLog = web3j.ethGetLogs(filter).send();

            if (ethLog.hasError()) {
                System.err.println("查询事件时出错：" + ethLog.getError().getMessage());
                return;
            }

            // 处理查询到的事件日志
            List<LotteryPool.LotteryDrawnEventResponse> events = contract.getLotteryDrawnEvents(ethLog);

            if (events.isEmpty()) {
                System.out.println("没有找到新的 LotteryDrawn 事件。");
            } else {
                for (LotteryPool.LotteryDrawnEventResponse event : events) {
                    System.out.println("查询到 LotteryDrawn 事件:");
                    System.out.println("  期号: " + event.round);
                    System.out.println("  奖池金额: " + event.prizePool);
                    System.out.println("  中奖者 Token IDs: " + event.winners);
                    // 在这里添加你的业务逻辑，例如将中奖信息写入数据库
                }
            }

            // 更新已处理的区块号
            lastProcessedBlock = latestBlock;
            System.out.println("已处理到区块: " + lastProcessedBlock);

        } catch (Exception e) {
            System.err.println("Web3j RPC 调用出错: " + e.getMessage());
        }*/
    }

}
