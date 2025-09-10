package org.lck.gx.service.impl;

import io.reactivex.Flowable;
import org.springframework.stereotype.Service;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.StaticArray16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/LFDT-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.7.0.
 */
@SuppressWarnings("rawtypes")
public class LotteryPool extends Contract {
    public static final String BINARY = "Bin file was not provided";

    public static final String FUNC_BPS_DENOMINATOR = "BPS_DENOMINATOR";

    public static final String FUNC_MIN_POOL_SIZE = "MIN_POOL_SIZE";

    public static final String FUNC_NFT_PRICE = "NFT_PRICE";

    public static final String FUNC_OVERFLOW_FEE_BPS = "OVERFLOW_FEE_BPS";

    public static final String FUNC_BUYNFT = "buyNFT";

    public static final String FUNC_CLAIMREWARD = "claimReward";

    public static final String FUNC_CURRENTROUND = "currentRound";

    public static final String FUNC_DEPOSITRESERVE = "depositReserve";

    public static final String FUNC_DRAWLOTTERY = "drawLottery";

    public static final String FUNC_GETPOOLS = "getPools";

    public static final String FUNC_LASTDRAWTIME = "lastDrawTime";

    public static final String FUNC_LCKTOKEN = "lckToken";

    public static final String FUNC_NFT = "nft";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PRIZEPOOL = "prizePool";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_RESERVEPOOL = "reservePool";

    public static final String FUNC_REWARDS = "rewards";

    public static final String FUNC_TOTALTOKENHELD = "totalTokenHeld";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event LOTTERYDRAWN_EVENT = new Event("LotteryDrawn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<StaticArray16<Uint256>>() {}));
    ;

    public static final Event NFTPURCHASED_EVENT = new Event("NFTPurchased", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event POOLSADJUSTED_EVENT = new Event("PoolsAdjusted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event RESERVEDEPOSITED_EVENT = new Event("ReserveDeposited", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event REWARDCLAIMED_EVENT = new Event("RewardClaimed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final CustomError OWNABLEINVALIDOWNER_ERROR = new CustomError("OwnableInvalidOwner", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final CustomError OWNABLEUNAUTHORIZEDACCOUNT_ERROR = new CustomError("OwnableUnauthorizedAccount", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final CustomError SAFEERC20FAILEDOPERATION_ERROR = new CustomError("SafeERC20FailedOperation", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    @Deprecated
    protected LotteryPool(String contractAddress, Web3j web3j, Credentials credentials,
                          BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected LotteryPool(String contractAddress, Web3j web3j, Credentials credentials,
                          ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected LotteryPool(String contractAddress, Web3j web3j,
                          TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected LotteryPool(String contractAddress, Web3j web3j,
                          TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> BPS_DENOMINATOR() {
        final Function function = new Function(FUNC_BPS_DENOMINATOR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> MIN_POOL_SIZE() {
        final Function function = new Function(FUNC_MIN_POOL_SIZE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> NFT_PRICE() {
        final Function function = new Function(FUNC_NFT_PRICE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> OVERFLOW_FEE_BPS() {
        final Function function = new Function(FUNC_OVERFLOW_FEE_BPS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> buyNFT() {
        final Function function = new Function(
                FUNC_BUYNFT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> claimReward(BigInteger tokenId,
            BigInteger round) {
        final Function function = new Function(
                FUNC_CLAIMREWARD, 
                Arrays.<Type>asList(new Uint256(tokenId),
                new Uint256(round)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> currentRound() {
        final Function function = new Function(FUNC_CURRENTROUND, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> depositReserve(BigInteger amount) {
        final Function function = new Function(
                FUNC_DEPOSITRESERVE, 
                Arrays.<Type>asList(new Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> drawLottery() {
        final Function function = new Function(
                FUNC_DRAWLOTTERY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getPools() {
        final Function function = new Function(FUNC_GETPOOLS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple2<BigInteger, BigInteger>>(function,
                new Callable<Tuple2<BigInteger, BigInteger>>() {
                    @Override
                    public Tuple2<BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> lastDrawTime() {
        final Function function = new Function(FUNC_LASTDRAWTIME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> lckToken() {
        final Function function = new Function(FUNC_LCKTOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> nft() {
        final Function function = new Function(FUNC_NFT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final Function function = new Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> prizePool() {
        final Function function = new Function(FUNC_PRIZEPOOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final Function function = new Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> reservePool() {
        final Function function = new Function(FUNC_RESERVEPOOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>> rewards(
            BigInteger param0, BigInteger param1) {
        final Function function = new Function(FUNC_REWARDS, 
                Arrays.<Type>asList(new Uint256(param0),
                new Uint256(param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<BigInteger, BigInteger, BigInteger, BigInteger> call() throws
                            Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<BigInteger, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> totalTokenHeld() {
        final Function function = new Function(FUNC_TOTALTOKENHELD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final Function function = new Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new Address(160, newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static List<LotteryDrawnEventResponse> getLotteryDrawnEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(LOTTERYDRAWN_EVENT, transactionReceipt);
        ArrayList<LotteryDrawnEventResponse> responses = new ArrayList<LotteryDrawnEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            LotteryDrawnEventResponse typedResponse = new LotteryDrawnEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.round = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.prizePool = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.winners = (List<BigInteger>) ((Array) eventValues.getNonIndexedValues().get(1)).getNativeValueCopy();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static LotteryDrawnEventResponse getLotteryDrawnEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(LOTTERYDRAWN_EVENT, log);
        LotteryDrawnEventResponse typedResponse = new LotteryDrawnEventResponse();
        typedResponse.log = log;
        typedResponse.round = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.prizePool = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.winners = (List<BigInteger>) ((Array) eventValues.getNonIndexedValues().get(1)).getNativeValueCopy();
        return typedResponse;
    }

    public Flowable<LotteryDrawnEventResponse> lotteryDrawnEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getLotteryDrawnEventFromLog(log));
    }

    public Flowable<LotteryDrawnEventResponse> lotteryDrawnEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOTTERYDRAWN_EVENT));
        return lotteryDrawnEventFlowable(filter);
    }

    public static List<NFTPurchasedEventResponse> getNFTPurchasedEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(NFTPURCHASED_EVENT, transactionReceipt);
        ArrayList<NFTPurchasedEventResponse> responses = new ArrayList<NFTPurchasedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            NFTPurchasedEventResponse typedResponse = new NFTPurchasedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.buyer = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.pricePaid = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static NFTPurchasedEventResponse getNFTPurchasedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(NFTPURCHASED_EVENT, log);
        NFTPurchasedEventResponse typedResponse = new NFTPurchasedEventResponse();
        typedResponse.log = log;
        typedResponse.buyer = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.pricePaid = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<NFTPurchasedEventResponse> nFTPurchasedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getNFTPurchasedEventFromLog(log));
    }

    public Flowable<NFTPurchasedEventResponse> nFTPurchasedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NFTPURCHASED_EVENT));
        return nFTPurchasedEventFlowable(filter);
    }

    public static List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static OwnershipTransferredEventResponse getOwnershipTransferredEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
        OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
        typedResponse.log = log;
        typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getOwnershipTransferredEventFromLog(log));
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public static List<PoolsAdjustedEventResponse> getPoolsAdjustedEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(POOLSADJUSTED_EVENT, transactionReceipt);
        ArrayList<PoolsAdjustedEventResponse> responses = new ArrayList<PoolsAdjustedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PoolsAdjustedEventResponse typedResponse = new PoolsAdjustedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.prizePool = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.reservePool = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static PoolsAdjustedEventResponse getPoolsAdjustedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(POOLSADJUSTED_EVENT, log);
        PoolsAdjustedEventResponse typedResponse = new PoolsAdjustedEventResponse();
        typedResponse.log = log;
        typedResponse.prizePool = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.reservePool = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<PoolsAdjustedEventResponse> poolsAdjustedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getPoolsAdjustedEventFromLog(log));
    }

    public Flowable<PoolsAdjustedEventResponse> poolsAdjustedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(POOLSADJUSTED_EVENT));
        return poolsAdjustedEventFlowable(filter);
    }

    public static List<ReserveDepositedEventResponse> getReserveDepositedEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(RESERVEDEPOSITED_EVENT, transactionReceipt);
        ArrayList<ReserveDepositedEventResponse> responses = new ArrayList<ReserveDepositedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            ReserveDepositedEventResponse typedResponse = new ReserveDepositedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ReserveDepositedEventResponse getReserveDepositedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RESERVEDEPOSITED_EVENT, log);
        ReserveDepositedEventResponse typedResponse = new ReserveDepositedEventResponse();
        typedResponse.log = log;
        typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ReserveDepositedEventResponse> reserveDepositedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getReserveDepositedEventFromLog(log));
    }

    public Flowable<ReserveDepositedEventResponse> reserveDepositedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RESERVEDEPOSITED_EVENT));
        return reserveDepositedEventFlowable(filter);
    }

    public static List<RewardClaimedEventResponse> getRewardClaimedEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(REWARDCLAIMED_EVENT, transactionReceipt);
        ArrayList<RewardClaimedEventResponse> responses = new ArrayList<RewardClaimedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RewardClaimedEventResponse typedResponse = new RewardClaimedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RewardClaimedEventResponse getRewardClaimedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(REWARDCLAIMED_EVENT, log);
        RewardClaimedEventResponse typedResponse = new RewardClaimedEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.tokenId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<RewardClaimedEventResponse> rewardClaimedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRewardClaimedEventFromLog(log));
    }

    public Flowable<RewardClaimedEventResponse> rewardClaimedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REWARDCLAIMED_EVENT));
        return rewardClaimedEventFlowable(filter);
    }

    @Deprecated
    public static LotteryPool load(String contractAddress, Web3j web3j, Credentials credentials,
                                   BigInteger gasPrice, BigInteger gasLimit) {
        return new LotteryPool(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static LotteryPool load(String contractAddress, Web3j web3j,
                                   TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new LotteryPool(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static LotteryPool load(String contractAddress, Web3j web3j, Credentials credentials,
                                   ContractGasProvider contractGasProvider) {
        return new LotteryPool(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static LotteryPool load(String contractAddress, Web3j web3j,
                                   TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new LotteryPool(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class LotteryDrawnEventResponse extends BaseEventResponse {
        public BigInteger round;

        public BigInteger prizePool;

        public List<BigInteger> winners;
    }

    public static class NFTPurchasedEventResponse extends BaseEventResponse {
        public String buyer;

        public BigInteger pricePaid;

        public BigInteger tokenId;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class PoolsAdjustedEventResponse extends BaseEventResponse {
        public BigInteger prizePool;

        public BigInteger reservePool;
    }

    public static class ReserveDepositedEventResponse extends BaseEventResponse {
        public String from;

        public BigInteger amount;
    }

    public static class RewardClaimedEventResponse extends BaseEventResponse {
        public String user;

        public BigInteger tokenId;

        public BigInteger amount;
    }
}
