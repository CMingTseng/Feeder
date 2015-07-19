package me.zsr.feeder.dao;

import me.zsr.feeder.dao.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table FEED_ITEM.
 */
public class FeedItem {

    private Long id;
    /** Not-null value. */
    private String title;
    private String link;
    private String description;
    private Boolean read;
    private Boolean star;
    private java.util.Date date;
    private long feedSourceId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient FeedItemDao myDao;

    private FeedSource feedSource;
    private Long feedSource__resolvedKey;


    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public FeedItem() {
    }

    public FeedItem(Long id) {
        this.id = id;
    }

    public FeedItem(Long id, String title, String link, String description, Boolean read, Boolean star, java.util.Date date, long feedSourceId) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.read = read;
        this.star = star;
        this.date = date;
        this.feedSourceId = feedSourceId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getFeedItemDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getTitle() {
        return title;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getStar() {
        return star;
    }

    public void setStar(Boolean star) {
        this.star = star;
    }

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public long getFeedSourceId() {
        return feedSourceId;
    }

    public void setFeedSourceId(long feedSourceId) {
        this.feedSourceId = feedSourceId;
    }

    /** To-one relationship, resolved on first access. */
    public FeedSource getFeedSource() {
        long __key = this.feedSourceId;
        if (feedSource__resolvedKey == null || !feedSource__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            FeedSourceDao targetDao = daoSession.getFeedSourceDao();
            FeedSource feedSourceNew = targetDao.load(__key);
            synchronized (this) {
                feedSource = feedSourceNew;
            	feedSource__resolvedKey = __key;
            }
        }
        return feedSource;
    }

    public void setFeedSource(FeedSource feedSource) {
        if (feedSource == null) {
            throw new DaoException("To-one property 'feedSourceId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.feedSource = feedSource;
            feedSourceId = feedSource.getId();
            feedSource__resolvedKey = feedSourceId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
