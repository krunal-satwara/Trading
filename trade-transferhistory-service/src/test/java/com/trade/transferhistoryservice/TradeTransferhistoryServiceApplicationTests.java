package com.trade.transferhistoryservice;

/*@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTransferhistoryServiceApplicationTests {

    private static final TransferHistory transferHistory = new TransferHistory(1L, 2L, "transferType", 100, 200, 300, 12L, "note", "createdDate");

    @Autowired
    TransferHistoryServiceImpl transferHistoryServiceImpl;

    @MockBean
    TransferRepository transferRepository;

    public TransferHistoryBean transferHistoryBean;

    @Before
    public void setUp(){
        transferHistoryBean = new TransferHistoryBean(1L, 2L, "transferType", 100, 200, 300, 12L, "note", "createdDate");
    }

    @Test
    public void createTransferHistory() {
        when(transferRepository.save(transferHistory)).thenReturn(transferHistory);
        assertEquals(transferHistory, transferHistoryServiceImpl.transferHistory(transferHistoryBean));
    }

    @Test
    public void getTransferHistory() {
        Long tid = transferHistory.getTransferId();
        when(transferRepository.findById(tid)).thenReturn(java.util.Optional.of(transferHistory));
        assertEquals(transferHistory, transferHistoryServiceImpl.transferHistory(tid).get());
    }

    @Test
    public void getTransferList() {
        when(transferRepository.findAll()).thenReturn(Stream.of(new TransferHistory(1L, 2L, "transferType", 100, 200, 300, 12L, "note", "createdDate")).collect(Collectors.toList()));
        assertEquals(1, transferHistoryServiceImpl.transferHistories().size());
    }
}*/
