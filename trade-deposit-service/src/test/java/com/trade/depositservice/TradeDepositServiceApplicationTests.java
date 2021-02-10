package com.trade.depositservice;

/*@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeDepositServiceApplicationTests {

    private static final Deposit deposit = new Deposit(1L, 2L, new BigDecimal(String.valueOf(12.2)), "transactionType", "createTime", "executeTIme", 1, "remarks");

    @Autowired
    private DepositServiceImpl depositServiceImpl;

    @MockBean
    private DepositRespository depositRespository;

    public DepositBean depositBean;

    @Before
    public void setUp() {
        depositBean = new DepositBean(1L, 2L, new BigDecimal(String.valueOf(12.2)), "transactionType", "createTime", "executeTIme", 1, "remarks");
    }

    @Test
    public void createDeposit() {
        when(depositRespository.save(deposit)).thenReturn(deposit);
        assertEquals(deposit, depositServiceImpl.deposit(depositBean));
    }

    @Test
    public void getDeposit() {
        Long did = deposit.getDepositeId();
        when(depositRespository.findById(did)).thenReturn(java.util.Optional.of(deposit));
        assertEquals(deposit, depositServiceImpl.deposit(did).get());
    }

    @Test
    public void getAllDepositsList() {
        when(depositRespository.findAll()).thenReturn(Stream.of(new Deposit(1L, 2L, new BigDecimal(String.valueOf(12.2)), "transactionType", "createTime", "executeTIme", 1, "remarks")).collect(Collectors.toList()));
        assertEquals(1, depositServiceImpl.deposits().size());
    }
}*/
