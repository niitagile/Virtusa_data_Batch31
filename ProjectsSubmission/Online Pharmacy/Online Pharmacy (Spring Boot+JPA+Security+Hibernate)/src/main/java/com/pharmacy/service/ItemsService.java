package com.pharmacy.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.model.DistributorItemBean;
import com.pharmacy.model.ItemsBean;
import com.pharmacy.model.ParticularOrderBean;
import com.pharmacy.repo.DistributorItemsRepo;
import com.pharmacy.repo.ItemsRepository;

@Service
public class ItemsService{


	@Autowired
	private ItemsRepository itemsRepository;

	@Autowired
	private DistributorItemService distributorItemService;

	@Autowired
	private DistributorItemsRepo distributorRepo;

	public ItemsService() {
	}

	public ItemsService(ItemsRepository itemsRepo) {
		super();
		this.itemsRepository = itemsRepo;
	}

	public void addItem(ItemsBean itemBean, DistributorItemBean distributorItem) {
		ItemsBean resultItemBean = null;
		if (!itemsRepository.existsByDistributor(itemBean.getDistributor())) {
			itemsRepository.save(itemBean);
		}

		resultItemBean = itemsRepository.findByDistributor(itemBean.getDistributor());
		distributorItem.setItemBean(resultItemBean);
		distributorItemService.addDisItem(distributorItem);
	}

	public boolean checkItem(ItemsBean itemBean) {
		ItemsBean item = this.itemsRepository.findByDistributor(itemBean.getDistributor());
		if (item == null)
			return false;
		else
			return true;
	}

	public ItemsBean insertIntoItemTable(ItemsBean item) {
		return this.itemsRepository.save(item);
	}

	@Transactional
	public int deleteByDistributor(String distributor) {
		return this.itemsRepository.deleteByDistributor(distributor);

	}


	@Transactional
	public void updateDistributorItem(String distributor,List<ParticularOrderBean> products) {
		int itemsId=this.getIdBydistributor(distributor);
		for(ParticularOrderBean product:products) {
			distributorItemService.updateItemAfterOrder(itemsId, product.getItemName(),product.getQuantity());
			}
	}
	
	public List<ItemsBean> getAllItems()

	{
		return this.itemsRepository.findAll();
	}

	public int getIdBydistributor(String distributor) {
		ItemsBean item = this.itemsRepository.findByDistributor(distributor);
		return item.getId();
	}

	public String getDistributorName(int id) {
		ItemsBean item = this.itemsRepository.findById(id);
		return item.getDistributor();
	}

	public ItemsBean getItemById(int id) {
		return itemsRepository.findById(id);
	}

	public List<DistributorItemBean> getDistributorItems(String distributor) {
		return distributorRepo.findByItemBean(itemsRepository.findByDistributor(distributor));
	}
	
	public void deleteItemsByDistributor(String distributor) {
		int id=itemsRepository.getIdByDistributor(distributor);
		distributorRepo.deleteByItemsId(id);
		itemsRepository.deleteByDistributor(distributor);
	}
}
