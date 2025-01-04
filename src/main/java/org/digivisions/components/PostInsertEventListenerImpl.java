package org.digivisions.components;

import org.digivisions.Services.NotificationService;
import org.digivisions.entities.EmployeeEntity;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostInsertEventListenerImpl implements PostInsertEventListener {

	@Autowired
	private NotificationService notificationService;

	@Override
	public void onPostInsert(PostInsertEvent event) {
		Object entity = event.getEntity();
		if(entity instanceof EmployeeEntity){
			notificationService.sendEmail(((EmployeeEntity) entity).getEmail(), "New Employee Created", "New employee registered successfully");
		}
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		return true;
	}

	@Override
	public boolean requiresPostCommitHandling(EntityPersister persister) {
		return true;
	}
}
