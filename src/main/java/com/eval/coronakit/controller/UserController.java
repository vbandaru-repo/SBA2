package com.eval.coronakit.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.eval.coronakit.entity.CoronaKit;
import com.eval.coronakit.entity.KitDetail;
import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.service.CoronaKitService;
import com.eval.coronakit.service.KitDetailService;
import com.eval.coronakit.service.ProductService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CoronaKitService coronaKitService;

	@Autowired
	KitDetailService kitDetailService;

	HttpSession session;
	
	@RequestMapping("/home")
	public String home() {

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		session = attr.getRequest().getSession();
		// fetching products list
		List<ProductMaster> products = productService.getAllProducts();
		// keeping in session to reduce database load
		session.setAttribute("productsCatalog", products);

		if (session.getAttribute("firstTime") == null)
			session.setAttribute("firstTime", "yes");
		/*CoronaKit coronakit = new CoronaKit();
		coronaKitService.saveKit(coronakit)*/;
		return "user-home";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/show-kit")
	public String showKit(Model model) {
		List<ProductMaster> products = (List<ProductMaster>)session.getAttribute("productsCatalog");
		List<KitDetail> kitList=(List<KitDetail>)session.getAttribute("kitsCatalogue");
		List<String[]> cartList;
		String[] strArr = new String[4];
		Map<Integer, String> productMap = products.stream()
				.collect(Collectors.toMap(x -> x.getId(), x -> x.getProductName()));
		
		cartList = new ArrayList<String[]>();
		if(kitList!=null)
		{
		for (KitDetail kd : kitList) {
			strArr = new String[4];
			strArr[0] = productMap.get(kd.getProductId());
			strArr[1] = String.valueOf(kd.getQuantity());
			strArr[2] = String.valueOf(kd.getAmount());
			strArr[3] = String.valueOf(kd.getId());
			cartList.add(strArr);
		}
		}
		else
			cartList.add(new String[] {"No Products Added","Proceed to List Products Page","n/a",""});
		model.addAttribute("cartList", cartList);
		return "show-cart";		
	}

	@RequestMapping("/show-list")
	public String showList(Model model) {
		@SuppressWarnings("unchecked")
		List<ProductMaster> products = (List<ProductMaster>)session.getAttribute("productsCatalog");//productService.getAllProducts();
		model.addAttribute("productList", products);
		return "show-all-item-user";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/add-to-cart/{productId}")
	public String showKit(@PathVariable("productId") int productId, Model model) {

		/*ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession();
		int coronaKitId = coronaKitService.getCurrrentId();*/
		//@SuppressWarnings("unchecked")
		List<ProductMaster> products = (List<ProductMaster>)session.getAttribute("productsCatalog");
		List<KitDetail> kitList=new ArrayList<KitDetail>();
		List<String[]> cartList;
		String[] strArr = new String[4];
		KitDetail kitdetail,temp;
		ProductMaster product = null;
		Map<Integer, String> productMap = products.stream()
				.collect(Collectors.toMap(x -> x.getId(), x -> x.getProductName()));

		// KitDetail kitdetail=kitDetailService.getKitItemById(productId);
		
		//finding product in the catalog
		for(ProductMaster desProduct:products)
		{
			if(desProduct.getId()==productId) {
				product=desProduct;
				break;
			}
		}
		//ProductMaster product = productService.getProductById(productId);
		if (session.getAttribute("firstTime").toString().equals("yes")) {
			kitdetail = new KitDetail();
			kitdetail.setAmount(product.getCost());
			//kitdetail.setCoronaKitId(coronaKitId);
			kitdetail.setProductId(productId);
			kitdetail.setQuantity(1);
			kitdetail.setId(kitList.size()+1);
			//saving kitdetails to the session to reduce db overload
			kitList.add(kitdetail);
			session.setAttribute("kitsCatalogue", kitList);
			//kitDetailService.addKitItem(kitdetail);
			session.setAttribute("firstTime", "no");
		} else {
			//fetching Kit details
			kitList = (List<KitDetail>)session.getAttribute("kitsCatalogue");//kitDetailService.getAllKitItemsOfAKit(coronaKitId);

			for (KitDetail kit : kitList) {
				if (kit.getProductId() == productId) {
					
					temp=kit;
					kitList.remove(kit);
					temp.setQuantity(kit.getQuantity() + 1);
					temp.setAmount(kit.getAmount() + product.getCost());
					kitList.add(temp);
					//kitDetailService.addKitItem(kit);
					//kitList = kitDetailService.getAllKitItemsOfAKit(coronaKitId);
					cartList = new ArrayList<String[]>();
					for (KitDetail kd : kitList) {
						strArr = new String[4];
						strArr[0] = productMap.get(kd.getProductId());
						strArr[1] = String.valueOf(kd.getQuantity());
						strArr[2] = String.valueOf(kd.getAmount());
						strArr[3] = String.valueOf(kd.getId());
						cartList.add(strArr);
					}
					model.addAttribute("cartList", cartList);
					session.setAttribute("kitsCatalogue", kitList);
					return "show-cart";
				}
			}
			kitdetail = new KitDetail();
			kitdetail.setAmount(product.getCost());
			//kitdetail.setCoronaKitId(coronaKitId);
			kitdetail.setProductId(productId);
			kitdetail.setQuantity(1);
			kitdetail.setId(kitList.size()+1);
			//upload kitDetails to session
			kitList.add(kitdetail);
			session.setAttribute("kitsCatalogue", kitList);
			//kitDetailService.addKitItem(kitdetail);

		}

		//kitList = kitDetailService.getAllKitItemsOfAKit(coronaKitId);
		cartList = new ArrayList<String[]>();
		
		for (KitDetail kd : kitList) {
			strArr = new String[4];
			strArr[0] = productMap.get(kd.getProductId());
			strArr[1] = String.valueOf(kd.getQuantity());
			strArr[2] = String.valueOf(kd.getAmount());
			strArr[3] = String.valueOf(kd.getId());
			cartList.add(strArr);
		}
		model.addAttribute("cartList", cartList);
		return "show-cart";
	}

	@RequestMapping("/checkout")
	public String checkout(Model model) {

		//int coronaKitId = coronaKitService.getCurrrentId();
		CoronaKit ck =new CoronaKit();// coronaKitService.getKitById(coronaKitId);
		@SuppressWarnings("unchecked")
		List<KitDetail> kitList = (List<KitDetail>)session.getAttribute("kitsCatalogue");
		ck.setOrderDate(LocalDate.now().toString());
		int totAmt = 0;
		// Total Amount amount computation
		for (KitDetail kd : kitList)
			totAmt += kd.getAmount();
		ck.setTotalAmount(totAmt);
		//coronaKitService.saveKit(ck);
		model.addAttribute("ck", ck);
		//put coronaKit to session
		session.setAttribute("coronaKit", ck);
		return "checkout-address";
	}

	@RequestMapping("/finalize")
	public String finalizeOrder(@ModelAttribute CoronaKit coronakit, Model model) {

		int coronakitid;
		CoronaKit finalKit = (CoronaKit)session.getAttribute("coronaKit");//coronaKitService.getKitById(coronaKitService.getCurrrentId());
		finalKit.setDeliveryAddress(coronakit.getDeliveryAddress());
		coronaKitService.saveKit(finalKit);
		int i = 1;
		@SuppressWarnings("unchecked")
		List<KitDetail> kitList= (List<KitDetail>)session.getAttribute("kitsCatalogue");
		@SuppressWarnings("unchecked")
		List<ProductMaster> products = (List<ProductMaster>)session.getAttribute("productsCatalog");
		List<String[]> cartList;
		String[] strArr = new String[4];

		Map<Integer, String> productMap = products.stream()
				.collect(Collectors.toMap(product -> product.getId(), product -> product.getProductName()));
		//kitList = kitDetailService.getAllKitItemsOfAKit(finalKit.getId());
		cartList = new ArrayList<String[]>();
		for (KitDetail kd : kitList) {
			strArr = new String[4];
			strArr[0] = String.valueOf(i);
			strArr[1] = productMap.get(kd.getProductId());
			strArr[2] = String.valueOf(kd.getQuantity());
			strArr[3] = String.valueOf(kd.getAmount());
			cartList.add(strArr);
			i++;
		}
		model.addAttribute("address", coronakit.getDeliveryAddress());
		model.addAttribute("date", finalKit.getOrderDate());
		model.addAttribute("amount", finalKit.getTotalAmount());
		model.addAttribute("cartList", cartList);
		//saving kit details in DB for audit
		coronakitid=coronaKitService.getCurrrentId();
		for(KitDetail dbKit:kitList)
		{
			dbKit.setCoronaKitId(coronakitid);
			dbKit.setId(0);
			kitDetailService.addKitItem(dbKit);
		}
		return "show-summary";
	}

	@RequestMapping("/delete/{itemId}")
	public String deleteItem(@PathVariable("itemId") int itemId, Model model) {
		//kitDetailService.deleteKit(itemId);
		//fetching kit details and deleting the respective kit
		@SuppressWarnings("unchecked")
		List<KitDetail> kitList = (List<KitDetail>)session.getAttribute("kitsCatalogue");
		@SuppressWarnings("unchecked")
		List<ProductMaster> products = (List<ProductMaster>)session.getAttribute("productsCatalog");
		for(KitDetail kitdetail:kitList)
		{
			if(kitdetail.getId()==itemId)
			{
				kitList.remove(kitdetail);
				break;
			}
		}
		//int coronaKitId = coronaKitService.getCurrrentId();
		List<String[]> cartList;
		String[] strArr = new String[4];
		// KitDetail kitdetail;

		Map<Integer, String> productMap = products.stream()
				.collect(Collectors.toMap(product -> product.getId(), product -> product.getProductName()));
		//kitList = kitDetailService.getAllKitItemsOfAKit(coronaKitId);
		cartList = new ArrayList<String[]>();
		for (KitDetail kd : kitList) {
			strArr = new String[4];
			strArr[0] = productMap.get(kd.getProductId());
			strArr[1] = String.valueOf(kd.getQuantity());
			strArr[2] = String.valueOf(kd.getAmount());
			strArr[3] = String.valueOf(kd.getId());
			cartList.add(strArr);
		}
		//System.out.println("Printing cart data");
		model.addAttribute("cartList", cartList);
		session.setAttribute("kitsCatalogue", kitList);
		return "show-cart";
	}
	@ExceptionHandler(Exception.class)
	public String customeError(Exception ex, Model model) {
		model.addAttribute("error", ex.toString());
		return "customerror-page";
	}
	
}
